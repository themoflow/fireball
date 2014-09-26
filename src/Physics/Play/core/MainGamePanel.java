package Physics.Play.core;

import Physics.Play.helpers.CollisionDetector;
import Physics.Play.drawableManagers.*;
import Physics.Play.drawables.*;
import Physics.Play.helpers.Drawer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.*;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private boolean logEnabled = true;
    public static float screenWidth;
    public static float screenHeight;
    private MainThread thread;
    private float length;
    private boolean dragged = false, down = false;
    private boolean rocketExploded;
    private boolean bulletExploded;
    private float[] rocketExplodeCoords;
    private float[] bulletExplodeCoords;
    private long prevSec, nowSec;
    private long prevSecBulletExplosion, nowSecBulletExplosion;
    private MyActivity activity;
    private int cityHits;
    private boolean gameOver;
    private int times, multiplyBy, theEnd;
    private CollisionDetector collisionDetector;

    //Drawable lists.
    private List<Robot> robots;
    private List<Rocket> rockets;
    private List<City> citys;
    private List<Explosion> explosions;
    private List<Fireball> fireballs;
    private List<ScreenBackground> screenBackgrounds;
    private List<GreenDot> greenDots;
    private List<Bullet> bullets;
    private List<BulletExplosion> bulletExplosions;

    //Drawable managers.
    private RobotManager robotManager = new RobotManager(this);
    private RocketManager rocketManager = RocketManager.getInstance();
    private CityManager cityManager = CityManager.getInstance();
    private ExplosionManager explosionManager = ExplosionManager.getInstance();
    private FireballManager fireballManager = FireballManager.getInstance();
    private ScreenBackgroundManager screenBackgroundManager = ScreenBackgroundManager.getInstance();
    private GreenDotManager greenDotManager = GreenDotManager.getInstance();
    private BulletManager bulletManager = BulletManager.getInstance();
    private BulletExplosionManager bulletExplosionManager = BulletExplosionManager.getInstance();

    private Drawer draw = Drawer.getInstance();
    private SlingShot slingShot;

    public MainGamePanel(Context context) {
        super(context);
        activity = (MyActivity)context;
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);
        // make the GamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidth = getWidth();
        screenHeight = getHeight();
        collisionDetector = CollisionDetector.getInstance();

        //Initialize fireball early because i need the Fireball width.
        Fireball.initializeStaticMembers(screenWidth, screenHeight, this);
        float slingDistance = (screenHeight - Fireball.getWidth()*2) - (screenHeight - (screenHeight/4));
        length = slingDistance / 10;

        //Initialize drawable objects.
        Rocket.initializeStaticMembers(this, screenWidth, screenHeight);
        Explosion.initializeStaticMembers(this);
        ScreenBackground.initializeStaticMembers(this);
        City.initializeStaticMembers(this);
        GreenDot.initializeStaticMembers(this);
        Robot.initializeStaticMembers(this);
        Bullet.initializeStaticMembers(this);
        BulletExplosion.initializeStaticMembers(this);

        //Create drawable lists.
        fireballs = fireballManager.createFireballs(1, this);
        robots = robotManager.createRobots(70);
        rockets = rocketManager.createRockets(70, this);
        citys = cityManager.createCitys(1, this, (screenWidth / 2) - 170, screenHeight - 120);
        explosions = explosionManager.createExplosions(0, this);
        screenBackgrounds = screenBackgroundManager.createScreenBackground(1, this, 0f, 0f);
        greenDots = greenDotManager.createGreenDots(1, this, fireballs);
        bullets = bulletManager.createBullets(0, this);
        bulletExplosions = bulletExplosionManager.createBulletExplosions(0, this);

        slingShot = new SlingShot(20, 10, screenHeight - 50, screenWidth - 10, screenHeight);

        //Add the robots to the rockets and get their movements coordinated..
        robotManager.addRobotsToRockets(rockets, robots);

        //Start main thread.
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry)
        {
            try
            {
                thread.setRunning(false);
                thread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
                System.out.println("InteruptedException error was caught in surfaceDestroyed method");
            }
        }
    } // end surfaceDestroyed()

    @Override
    synchronized public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            down = fireballManager.checkForFireballTouch(event.getX(), event.getY(), fireballs);
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            if(dragged)
            {
                dragged = false;
                down = false;
                fireballManager.getAndSetVelocity(event.getX(), event.getY(), length, fireballs);
                greenDotManager.reset(greenDots);
            }

        }

        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            if(down)
            {
               dragged = true;
               fireballManager.onDrag(event.getX(), event.getY(), fireballs, this);
               greenDotManager.move(length, greenDots, fireballs);
            }
        }
        return true;

    } //end onTouchEvent()

    @Override
    protected void onDraw(Canvas canvas) {

     super.onDraw(canvas);
     if(canvas != null)
     {
         if(gameOver)
         {
             canvas.drawColor(Color.BLACK);
             draw.drawToCanvas(screenBackgroundManager.setAsDrawable(screenBackgrounds), canvas);
             draw.drawToCanvas(cityManager.setAsDrawable(citys), canvas);
             if(times*40 < screenWidth)
             {
                 explosions = explosionManager.createExplosions(1, this);
                 explosionManager.setCoordinates(times * 40, screenHeight - (City.getHeight() - 40), explosions);
                 draw.drawToCanvas(explosionManager.setAsDrawable(explosions), canvas);
                 times++;
             }
             else if(multiplyBy*40 < screenWidth)
             {
                 explosions = explosionManager.createExplosions(1, this);
                 explosionManager.setCoordinates((multiplyBy * 40), (screenHeight - (City.getHeight() / 2)), explosions);
                 draw.drawToCanvas(explosionManager.setAsDrawable(explosions), canvas);
                 multiplyBy++;
             }
             theEnd++;
             if(theEnd == 15)
             {
                thread.setRunning(false);
                activity.showDialog("GAME OVER\nYou let the city get destroyed\nTry Again!");
             }
         }
         else
         {
             fireballManager.isAtleastOneFireball(fireballs, this);
             fireballManager.checkForFireBallRemoval(fireballs, this);
             fireballManager.checkForFireballCreation(down, fireballs, this);
             fireballManager.addVelocity(fireballs);
             rocketManager.move(rockets);
             robotManager.move(robots);
             bullets = bulletManager.createBullets(robots, bullets, this);
             bulletManager.move(bullets);

             float[] fireballRocketCollionCoordinates = collisionDetector.fireBallColidedWithRockets(fireballs, rockets, robots);
             float[] rocketCityCollisionCoordinates = collisionDetector.rocketCollidedWithCity(rockets, citys, robots);
             float[] fireballRocketCollisionCoordinates = collisionDetector.fireBallColidedWithRobots(fireballs, robots, rockets);
             float[] bulletCityCollisionCoordinates = collisionDetector.bulletsCollidedWithCity(bullets, citys);

             if (!(fireballRocketCollionCoordinates == null))
                 rocketExplosion(true, fireballRocketCollionCoordinates);

             if (!(rocketCityCollisionCoordinates == null))
             {
                 this.addHitToCity();
                 rocketExplosion(true, rocketCityCollisionCoordinates);
             }

             if (!(fireballRocketCollisionCoordinates == null)) {
                 rocketExplosion(true, fireballRocketCollisionCoordinates);
             }

             if (!(bulletCityCollisionCoordinates == null)) {
                 bulletExplosion(true, bulletCityCollisionCoordinates);
             }

             //Check to see if rocket explosion animation needs to be played.
             if(rocketExploded) {
                 if (prevSec == 0)
                 {
                     log("rocket exploded 0 ===============================");
                     prevSec = System.currentTimeMillis();
                     explosions = explosionManager.createExplosions(1, this);
                     explosionManager.setCoordinates(rocketExplodeCoords[0], rocketExplodeCoords[1], explosions);
                     explosionManager.setIsActive(true, explosions);
                 }
                 else
                 {
                     log("rocket exploded 1 ===============================");
                     nowSec = System.currentTimeMillis();
                     if (nowSec - prevSec > 100)
                     {
                         rocketExploded = false;
                         prevSec = 0;
                         explosions = explosionManager.createExplosions(0, this);
                     }
                     else
                     {
                         log("rocket exploded 2 ===============================");
                         explosions = explosionManager.createExplosions(1, this);
                         explosionManager.setCoordinates(rocketExplodeCoords[0] - 25, rocketExplodeCoords[1] - 25, explosions);
                         explosionManager.setIsActive(true, explosions);
                     }

                 }
                 if (cityHits == 10) {
                     gameOver = true;
                 }
             }

             //Check to see if bullet explosion animation needs to be played.
             if(bulletExploded) {
                 if(prevSecBulletExplosion == 0)
                 {
                     log("bullet exploded 0 ===============================");
                     prevSecBulletExplosion = System.currentTimeMillis();
                     bulletExplosions = bulletExplosionManager.createBulletExplosions(1, this);
                     bulletExplosionManager.setCoordinates(bulletExplodeCoords[0], bulletExplodeCoords[1], bulletExplosions);
                     bulletExplosionManager.setIsActive(true, bulletExplosions);
                 }
                 else
                 {
                     nowSecBulletExplosion = System.currentTimeMillis();
                     if (nowSecBulletExplosion - prevSecBulletExplosion > 100)
                     {
                         log("bullet exploded 1 ===============================");
                         bulletExploded = false;
                         prevSecBulletExplosion = 0;
                         bulletExplosions = bulletExplosionManager.createBulletExplosions(0, this);
                     }
                     else
                     {
                         log("bullet exploded 2 ===============================");
                         bulletExplosions = bulletExplosionManager.createBulletExplosions(1, this);
                         bulletExplosionManager.setCoordinates(bulletExplodeCoords[0], bulletExplodeCoords[1], bulletExplosions);
                         bulletExplosionManager.setIsActive(true, bulletExplosions);
                     }

                 }
                 if (cityHits == 10) {
                     gameOver = true;
                 }
             }

             float[] aimerCoordinates = greenDotManager.getAimerCoordinates(greenDots);

             //Redraw the whole canvas.
             draw.backgroundColor(Color.BLACK, canvas);
             draw.drawToCanvas(screenBackgroundManager.setAsDrawable(screenBackgrounds), canvas);
             draw.drawToCanvas(cityManager.setAsDrawable(citys), canvas);
             draw.drawToCanvas(fireballManager.setAsDrawable(fireballs), canvas);
             draw.drawToCanvas(rocketManager.setAsDrawable(rockets), canvas);
             draw.drawToCanvas(explosionManager.setAsDrawable(explosions), canvas);
             draw.drawToCanvas(robotManager.setAsDrawable(robots), canvas);
             draw.drawToCanvas(bulletManager.setAsDrawable(bullets), canvas);
             draw.drawToCanvas(bulletExplosionManager.setAsDrawable(bulletExplosions), canvas);
             //draw.drawToCanvas(greenDotManager.setAsDrawable(greenDots), canvas);
             draw.drawLine(aimerCoordinates[0], aimerCoordinates[1], aimerCoordinates[2], aimerCoordinates[3], canvas);
             //draw.drawArch(slingShot.getLeft(), slingShot.getTop(), slingShot.getRight(), slingShot.getBottom(), canvas);
         }

     }//end null canvas check.

    }//end onDraw

    public void rocketExplosion(boolean b, float[] c){
        rocketExploded = b;
        rocketExplodeCoords = c;
    }

    public void bulletExplosion(boolean b, float[] c){
        bulletExploded = b;
        bulletExplodeCoords = c;
    }

    public void addHitToCity(){
        cityHits++;
    }

    public boolean gameOver(){
        return gameOver;
    }

    public static float getScreenWidth(){
        return screenWidth;
    }

    public static float getScreenHeight(){
        return screenHeight;
    }

    public void log(String string) {
        if(logEnabled)
            Log.i("MainGamePanel - ",string);
    }

}