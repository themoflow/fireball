package Physics.Play.main;



import Physics.Play.Logic.Collision;
import Physics.Play.Managers.*;
import Physics.Play.drawables.*;
import Physics.Play.Logic.Drawer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.*;


public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static float screenWidth;
    public static float screenHeight;
    private MainThread thread;
    private float xOrig;
    private float yOrig;
    private float length;
    private boolean dragged = false, down = false;
    private boolean exploded;
    private float[] explodeCoords;
    private long prevSec, nowSec;
    private MyActivity activity;
    private float cityX, cityY;
    private int cityHits;
    private boolean gameOver;
    private int times, multiplyBy, theEnd;
    private List<Robot> robots;
    private List<Rocket> rockets;
    private List<City> citys;
    private List<Explosion> explosions;
    private List<Fireball> fireballs;
    private List<ScreenBackground> screenBackgrounds;
    private Drawer draw = new Drawer();
    private RobotManager robotManager = new RobotManager(this);
    private RocketManager rocketManager = RocketManager.getInstance();
    private CityManager cityManager = CityManager.getInstance();
    private ExplosionManager explosionManager = ExplosionManager.getInstance();
    private FireballManager fireballManager = FireballManager.getInstance();
    private ScreenBackgroundManager screenBackgroundManager = ScreenBackgroundManager.getInstance();
    private Collision collision;
    private boolean logEnabled = false;

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
        xOrig = (screenWidth/2) - (Fireball.getWidth()/2);
        yOrig = (screenHeight - (screenHeight/4));
        cityX = (screenWidth/2) - 170;
        cityY = screenHeight - 120;
        float slingDistance = (screenHeight - Fireball.getWidth()*2) - (yOrig);
        length = slingDistance / 10;
        Fireball.initializeStaticMembers(xOrig, yOrig, this);
        Rocket.initializeStaticMembers(this, screenWidth, screenHeight);
        Explosion.initializeStaticMembers(this);
        ScreenBackground.initializeStaticMembers(this);
        City.initializeStaticMembers(this);
        fireballs = fireballManager.createFireballs(1, this, xOrig, yOrig);
        robots = robotManager.createRobots(20);
        rockets = rocketManager.createRockets(70, this);
        citys = cityManager.createCitys(1, this, cityX, cityY);
        explosions = explosionManager.createExplosions(0, this);
        screenBackgrounds = screenBackgroundManager. createScreenBackground(1, this, 0f, 0f);
        collision = new Collision(this);
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
            log("onTouchEvent - event.");
            down = fireballManager.checkForFireballTouch(event.getX(), event.getY(), fireballs);
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            if(dragged)
            {
                dragged = false;
                down = false;
                fireballManager.getAndSetVelocity(event.getX(), event.getY(), length, fireballs);
            }

        }

        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            if(down)
            {
               log("ACTION_MOVE");
               dragged = true;
               fireballManager.onDrag(event.getX(), event.getY(), fireballs, this);
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

             float[] coords = collision.fireBallColided(fireballs, rockets);
             float[] coords2 = collision.rocketCollidedWithCity(rockets, citys);
             if (!(coords == null))
                 this.explosion(true, coords);

             if (!(coords2 == null)) {
                 this.addHitToCity();
                 this.explosion(true, coords2);
             }

             //Check to see if explosion animation needs to be played.
             if (exploded) {
                 if (prevSec == 0) {
                     prevSec = System.currentTimeMillis();
                     explosions = explosionManager.createExplosions(1, this);
                     explosionManager.setCoordinates(explodeCoords[0] - 25, explodeCoords[1] - 25, explosions);
                     explosionManager.setIsActive(true, explosions);
                 } else {
                     nowSec = System.currentTimeMillis();
                     if (nowSec - prevSec > 100) {
                         exploded = false;
                         prevSec = 0;
                         explosions = explosionManager.createExplosions(0, this);
                     } else {
                         explosions = explosionManager.createExplosions(1, this);
                         explosionManager.setCoordinates(explodeCoords[0] - 50, explodeCoords[1] - 50, explosions);
                         explosionManager.setIsActive(true, explosions);
                     }

                 }
                 if (cityHits == 5) {
                     gameOver = true;
                 }
             }

             //Redraw the whole canvas.
             draw.backgroundColor(Color.BLACK, canvas);
             draw.drawToCanvas(screenBackgroundManager.setAsDrawable(screenBackgrounds), canvas);
             draw.drawToCanvas(cityManager.setAsDrawable(citys), canvas);
             draw.drawToCanvas(fireballManager.setAsDrawable(fireballs), canvas);
             draw.drawToCanvas(rocketManager.setAsDrawable(rockets), canvas);
             draw.drawToCanvas(explosionManager.setAsDrawable(explosions), canvas);
         }

     }//end null canvas check.

    }//end onDraw

    public void explosion(boolean b, float[] c){
        exploded = b;
        explodeCoords = c;
    }

    public float getCityX(){
        return cityX;
    }

    public float getCityY(){
        return cityY;
    }

    public void addHitToCity(){
        cityHits++;
    }

    public boolean gameOver(){
        return gameOver;
    }

    public float getScrWidth(){
        return screenWidth;
    }

    public float getScrHeight(){
        return screenHeight;
    }

    public void log(String string) {
        if(logEnabled)
            Log.i("MainGamePanel - ",string);
    }

}
