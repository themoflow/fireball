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
    private int times, multiplyBy, theEnd;
    private CollisionDetector collisionDetector;

    //Drawable lists.
    private List<Robot> robots;
    private List<Rocket> rockets;
    private List<City> citys;
    private List<RocketExplosion> rocketExplosions = new ArrayList();
    private List<Fireball> fireballs;
    private List<ScreenBackground> screenBackgrounds;
    private List<GreenDot> greenDots;
    private List<Bullet> bullets;
    private List<BulletExplosion> bulletExplosions = new ArrayList();
    private List<Parachute> parachutes = new ArrayList();
    private List<RobotExplosion> robotExplosions = new ArrayList();
    private List<AtomicExplosion> atomicExplosions = new ArrayList();
    private List<CityExplosion> cityExplosions = new ArrayList();

    //Drawable managers.
    private RobotManager robotManager = RobotManager.getInstance();
    private RocketManager rocketManager = RocketManager.getInstance();
    private CityManager cityManager = CityManager.getInstance();
    private RocketExplosionManager rocketExplosionManager = RocketExplosionManager.getInstance();
    private FireballManager fireballManager = FireballManager.getInstance();
    private ScreenBackgroundManager screenBackgroundManager = ScreenBackgroundManager.getInstance();
    private GreenDotManager greenDotManager = GreenDotManager.getInstance();
    private BulletManager bulletManager = BulletManager.getInstance();
    private BulletExplosionManager bulletExplosionManager = BulletExplosionManager.getInstance();
    private SlingShotManager slingShotManager = SlingShotManager.getInstance();
    private ParachuteManager parachuteManager = ParachuteManager.getInstance();
    private RobotExplosionManager robotExplosionManager = RobotExplosionManager.getInstance();
    private AtomicExplosionManager atomicExplosionManager = AtomicExplosionManager.getInstance();
    private CityExplosionManager cityExplosionManager = CityExplosionManager.getInstance();

    private Drawer draw = Drawer.getInstance();
    private SlingShot slingShot;

    public static boolean GAME_OVER = false;
    private boolean gameOverSequenceHasStarted = false;

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
        RocketExplosion.initializeStaticMembers(this);
        ScreenBackground.initializeStaticMembers(this);
        City.initializeStaticMembers(this);
        GreenDot.initializeStaticMembers(this);
        Robot.initializeStaticMembers(this);
        Bullet.initializeStaticMembers(this);
        BulletExplosion.initializeStaticMembers(this);
        Parachute.initializeStaticMembers(this);
        RobotExplosion.initializeStaticMembers(this);
        AtomicExplosion.initializeStaticMembers(this);
        CityExplosion.initializeStaticMembers(this);

        //Create drawable lists.
        fireballs = fireballManager.createFireballs(1, this);
        robots = robotManager.createRobots(70, this);
        rockets = rocketManager.createRockets(70, this);
        citys = cityManager.createCitys(1, this, (screenWidth / 2) - 170, screenHeight - 120);
        screenBackgrounds = screenBackgroundManager.createScreenBackground(1, this, 0f, 0f);
        greenDots = greenDotManager.createGreenDots(1, this, fireballs);
        bullets = bulletManager.createBullets(0, this);
        parachutes = parachuteManager.createParachutes(70, this);

        slingShot = new SlingShot(screenWidth / 2, screenWidth - 10, (screenHeight - (screenHeight / 4)) + (Fireball.getHeight() / 2));

        //Add the robots and the rockets to each other and get their movements coordinated..
        robotManager.addRobotsToRockets(rockets, robots);
        parachuteManager.addParachutesToRobots(parachutes, robots);

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
               slingShotManager.move(fireballs, slingShot);
            }
        }
        return true;

    } //end onTouchEvent()

    @Override
    protected void onDraw(Canvas canvas) {

     super.onDraw(canvas);

     if(canvas != null)
     {
         if(GAME_OVER)
         {
             if(!gameOverSequenceHasStarted)
             {
                 gameOverSequenceHasStarted = true;
                 cityExplosionManager.createExplosion(this, cityExplosions, screenWidth, screenHeight, "left");
                 cityExplosionManager.createExplosion(this, cityExplosions, screenWidth, screenHeight, "right");
                 atomicExplosionManager.createExplosion(this, atomicExplosions, screenWidth, screenHeight);
                 cityManager.setDestroyedCityImage(citys);
                 canvas.drawColor(Color.BLACK);
                 draw.drawToCanvas(screenBackgroundManager.setAsDrawable(screenBackgrounds), canvas);
                 draw.drawToCanvas(cityManager.setAsDrawable(citys), canvas);
                 draw.drawToCanvas(cityExplosionManager.setAsDrawable(cityExplosions), canvas);
                 draw.drawToCanvas(atomicExplosionManager.setAsDrawable(atomicExplosions), canvas);
             }
             else
             {
                 atomicExplosionManager.checkForRemoval(atomicExplosions);
                 cityExplosionManager.checkForRemoval(cityExplosions);
                 canvas.drawColor(Color.BLACK);
                 draw.drawToCanvas(screenBackgroundManager.setAsDrawable(screenBackgrounds), canvas);
                 draw.drawToCanvas(cityManager.setAsDrawable(citys), canvas);
                 draw.drawToCanvas(cityExplosionManager.setAsDrawable(cityExplosions), canvas);
                 draw.drawToCanvas(atomicExplosionManager.setAsDrawable(atomicExplosions), canvas);
                 if(atomicExplosions.size() == 0 && cityExplosions.size() == 0)
                 {
                     thread.setRunning(false);
                     City.HITS = 0;
                     GAME_OVER = false;
                     activity.showDialog("GAME OVER\nYou let the city get destroyed\nTry Again!");
                 }
             }
             /*rocketExplosionManager.checkForRemoval(rocketExplosions);
             bulletExplosionManager.checkForRemoval(bulletExplosions);
             canvas.drawColor(Color.BLACK);
             draw.drawToCanvas(screenBackgroundManager.setAsDrawable(screenBackgrounds), canvas);
             draw.drawToCanvas(cityManager.setAsDrawable(citys), canvas);
             if(times*40 < screenWidth)
             {
                 rocketExplosions = rocketExplosionManager.createExplosions(1, this, rocketExplosions);
                 rocketExplosionManager.setCoordinates(times * 40, screenHeight - (City.getHeight() - 40), rocketExplosions);
                 draw.drawToCanvas(rocketExplosionManager.setAsDrawable(rocketExplosions), canvas);
                 times++;
             }
             else if(multiplyBy*40 < screenWidth)
             {
                 rocketExplosions = rocketExplosionManager.createExplosions(1, this, rocketExplosions);
                 rocketExplosionManager.setCoordinates((multiplyBy * 40), (screenHeight - (City.getHeight() / 2)), rocketExplosions);
                 draw.drawToCanvas(rocketExplosionManager.setAsDrawable(rocketExplosions), canvas);
                 multiplyBy++;
             }
             theEnd++;
             if(theEnd == 15)
             {
                thread.setRunning(false);
                City.HITS = 0;
                activity.showDialog("GAME OVER\nYou let the city get destroyed\nTry Again!");
             }*/
         }
         else
         {
             fireballManager.isAtleastOneFireball(fireballs, this);
             fireballManager.checkForFireBallRemoval(fireballs, this);
             fireballManager.checkForFireballCreation(down, fireballs, this);
             fireballManager.addVelocity(fireballs);
             rocketManager.move(rockets);
             robotManager.move(robots, collisionDetector, this);
             parachuteManager.move(parachutes);
             bullets = bulletManager.createBullets(robots, bullets, this);
             bulletManager.move(bullets);
             slingShotManager.move(fireballs, slingShot);
             rocketExplosionManager.checkForRemoval(rocketExplosions);
             bulletExplosionManager.checkForRemoval(bulletExplosions);
             robotExplosionManager.checkForRemoval(robotExplosions);

             float[] fireballRocketCollionCoordinates = collisionDetector.fireBallColidedWithRockets(fireballs, rockets, robots);
             float[] rocketCityCollisionCoordinates = collisionDetector.rocketCollidedWithCity(rockets, citys, robots);
             float[] fireballRobotCollisionCoordinates = collisionDetector.fireBallColidedWithRobots(fireballs, robots, rockets, parachutes);
             float[] bulletCityCollisionCoordinates = collisionDetector.bulletsCollidedWithCity(bullets, citys);
             float[] fireballBulletCollisionCoordinates = collisionDetector.fireBallColidedWithBullets(fireballs, bullets);

             if(fireballRocketCollionCoordinates != null)
             {
                 rocketExplosionManager.createExplosion(this, rocketExplosions, fireballRocketCollionCoordinates[0] - (RocketExplosion.getWidth()/2), fireballRocketCollionCoordinates[1]);
             }

             if(rocketCityCollisionCoordinates != null)
             {
                 if(City.HITS++ == 5)
                     GAME_OVER = true;
                 rocketExplosionManager.createExplosion(this, rocketExplosions, rocketCityCollisionCoordinates[0] - (RocketExplosion.getWidth()/2), rocketCityCollisionCoordinates[1] - (RocketExplosion.getHeight() / 2));
             }

             if(fireballRobotCollisionCoordinates != null)
             {
                  robotExplosionManager.createRobotExplosion(this, robotExplosions, fireballRobotCollisionCoordinates[0] - (RocketExplosion.getWidth() / 2), fireballRobotCollisionCoordinates[1]);
             }

             if(bulletCityCollisionCoordinates != null)
             {
                 //addHitToCity();
                 bulletExplosionManager.createBulletExplosion(this, bulletExplosions, bulletCityCollisionCoordinates[0] - (BulletExplosion.getWidth()/2), bulletCityCollisionCoordinates[1]);
             }
             if(fireballBulletCollisionCoordinates != null)
             {
                 bulletExplosionManager.createBulletExplosion(this, bulletExplosions, fireballBulletCollisionCoordinates[0] - (BulletExplosion.getWidth()/2), fireballBulletCollisionCoordinates[1]);
             }

             float[] aimerCoordinates = greenDotManager.getAimerCoordinates(greenDots);

             //Redraw the whole canvas.
             draw.backgroundColor(Color.BLACK, canvas);
             draw.drawToCanvas(screenBackgroundManager.setAsDrawable(screenBackgrounds), canvas);
             draw.drawToCanvas(cityManager.setAsDrawable(citys), canvas);
             draw.drawToCanvas(fireballManager.setAsDrawable(fireballs), canvas);
             draw.drawToCanvas(rocketManager.setAsDrawable(rockets), canvas);
             draw.drawToCanvas(rocketExplosionManager.setAsDrawable(rocketExplosions), canvas);
             draw.drawToCanvas(parachuteManager.setAsDrawable(parachutes), canvas);
             draw.drawToCanvas(robotManager.setAsDrawable(robots), canvas);
             draw.drawToCanvas(bulletManager.setAsDrawable(bullets), canvas);
             draw.drawToCanvas(robotExplosionManager.setAsDrawable(robotExplosions), canvas);
             draw.drawToCanvas(bulletExplosionManager.setAsDrawable(bulletExplosions), canvas);
             draw.drawLine(slingShot.getLeftX(), slingShot.getTopY(), slingShot.getMidX(), slingShot.getBottomY(), canvas);
             draw.drawLine(slingShot.getMidX(), slingShot.getBottomY(), slingShot.getRightX(), slingShot.getTopY(), canvas);
             draw.drawLine(aimerCoordinates[0], aimerCoordinates[1], aimerCoordinates[2], aimerCoordinates[3], canvas);


             //Testing, for the path of the jumping robots.
             /*List<GreenDot> plottedCurve = new ArrayList();
             for(int i = 0; i < robots.size(); i ++)
             {
                 List<Coordinate> c = robots.get(i).getJumpCoordinates();
                 if(c != null) {
                     for (int j = 0; j < c.size(); j++)
                         plottedCurve.add(new GreenDot(this, c.get(j).getX(), c.get(j).getY()));
                     draw.drawToCanvas(greenDotManager.setAsDrawable(plottedCurve), canvas);
                 }
             }*/
             //Testing end.

         }

     }//end null canvas check.

    }//end onDraw

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
