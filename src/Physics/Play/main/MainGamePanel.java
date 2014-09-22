package Physics.Play.main;



import Physics.Play.Logic.Collision;
import Physics.Play.Managers.CityManager;
import Physics.Play.Managers.ExplosionManager;
import Physics.Play.Managers.RobotManager;
import Physics.Play.Managers.RocketManager;
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
    private Paint paint;
    private MainThread thread;
    private float x;
    private float y;
    private float xOrig;
    private float yOrig;
    private float speed = 100;
    private float angle = 0;
    private float length;
    private float xVelocity = 0, yVelocity = 0, acceleration = 0.3f;
    private boolean dragged = false, down = false;
    private boolean exploded;
    private float[] explodeCoords;
    private long prevSec, nowSec;
    private MyActivity activity;
    private float cityX, cityY;
    private int cityHits;
    private Random rand = new Random();
    private boolean gameOver;
    private int times, multiplyBy, theEnd;
    private int level = 1;
    private Fireball fireball;
    private ScreenBackground screenBackground;
    private Drawer drawer;
    private List<Drawable> drawables = new ArrayList();
    private List<Robot> robots;
    private List<Rocket> rockets;
    private List<City> citys;
    private List<Explosion> explosions;
    private List<Fireball> fireballs = new ArrayList();
    private Drawer draw = new Drawer();
    private RobotManager robotManager = new RobotManager(this);
    private RocketManager rocketManager = RocketManager.getInstance();
    private CityManager cityManager = CityManager.getInstance();
    private ExplosionManager explosionManager = ExplosionManager.getInstance();
    private Collision collision;
    private boolean logEnabled = false;

    public MainGamePanel(Context context) {
        super(context);
        activity = (MyActivity)context;
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);
        // make the GamePanel focusable so it can handle events
        setFocusable(true);
        paint = new Paint();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidth = getWidth();
        screenHeight = getHeight();
        xOrig = (screenWidth/2) - (Fireball.getWidth()/2);
        yOrig = (screenHeight - (screenHeight/4));
        Fireball.initialize(xOrig, yOrig);
        fireball = new Fireball(this);
        fireball.addFireball(xOrig,yOrig);
        x = xOrig;
        y = yOrig;
        cityX = (screenWidth/2) - 170;
        cityY = screenHeight - 120;
        float slingDistance = (screenHeight - Fireball.getWidth()*2) - (yOrig);
        length = slingDistance / 10;
        screenBackground = new ScreenBackground(this, 0f, 0f);
        level = 2;
        robots = robotManager.createRobots(20);
        rockets = rocketManager.createRockets(70, this);
        citys = cityManager.createCitys(1, this, cityX, cityY);
        explosions = explosionManager.createExplosions(0, this);
        fireballs.add(fireball);
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
            down = fireball.checkForFireballTouch(event.getX(), event.getY());
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            if(dragged)
            {
                dragged = false;
                down = false;
                fireball.getAndSetVelocity(event.getX(), event.getY(), length);
            }

        }

        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            if(down)
            {
               log("ACTION_MOVE");
               dragged = true;
               fireball.move(event.getX(), event.getY());
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
             citys.get(0).draw(canvas);
             if(times*40 < screenWidth)
             {
                 explosions = explosionManager.createExplosions(1, this);
                 explosions.get(0).setCoordinates(times * 40, screenHeight - (City.getHeight() - 40));
                 explosions.get(0).draw(canvas);
                 times++;
             }
             else if(multiplyBy*40 < screenWidth)
             {
                 explosions = explosionManager.createExplosions(1, this);
                 explosions.get(0).setCoordinates(multiplyBy * 40, screenHeight - (City.getHeight() / 2));
                 explosions.get(0).draw(canvas);
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
             fireball.checkFor0Fireballs();
             fireball.checkForFireBallRemoval();
             fireball.checkForFireballCreation(down);
             fireball.addVeloc();
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
                     explosions.get(0).setCoordinates(explodeCoords[0] - 25, explodeCoords[1] - 25);
                     explosions.get(0).setIsActive(true);
                 } else {
                     nowSec = System.currentTimeMillis();
                     if (nowSec - prevSec > 100) {
                         exploded = false;
                         prevSec = 0;
                         explosions = new ArrayList();
                     } else {
                         explosions = explosionManager.createExplosions(1, this);
                         explosions.get(0).setCoordinates(explodeCoords[0] - 50, explodeCoords[1] - 50);
                         explosions.get(0).setIsActive(true);
                     }

                 }
                 if (cityHits == 5) {
                     gameOver = true;
                 }
             }

             //Redraw the whole canvas.
             draw.backgroundColor(Color.BLACK, canvas);
             draw.drawToCanvas(cityManager.setAsDrawable(citys), canvas);
             draw.drawFireballs(fireballs, canvas);
             draw.drawToCanvas(rocketManager.setAsDrawable(rockets), canvas);
             draw.drawToCanvas(explosionManager.setAsDrawable(explosions), canvas);
         }

     }//end null canvas check.

    }//end onDraw

    public void setLevel(int num){
        level = num;
    }

    public void explosion(boolean b, float[] c){
        exploded = b;
        explodeCoords = c;
    }

    public void explosion(boolean b){
        exploded = b;
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

    public City getCity(){
        return citys.get(0);
    }

    public void log(String string) {
        if(logEnabled)
            Log.i("MainGamePanel - ",string);
    }

}
