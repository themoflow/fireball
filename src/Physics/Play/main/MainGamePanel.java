package Physics.Play.main;



import Physics.Play.drawables.*;
import Physics.Play.Logic.Drawer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
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
    private Rocket rocket;
    private City city;
    private Explosion explosion;
    private Drawer drawer;
    private List<Drawable> drawables = new ArrayList();
    private Drawer draw = new Drawer();


    public MainGamePanel(Context context) {
        super(context);
        activity = (MyActivity)context;
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);
        // make the GamePanel focusable so it can handle events
        setFocusable(true);
        paint = new Paint();
    }

    public MainGamePanel(Context context, int level, int numOfSqrs) {
        super(context);
        activity = (MyActivity)context;
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);
        // create the game loop thread
        // make the GamePanel focusable so it can handle events
        setFocusable(true);
        paint = new Paint();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidth = getWidth();
        screenHeight = getHeight();
        xOrig = (screenWidth/2) - (Fireball.getWidth()/2);
        yOrig = (screenHeight - (screenHeight/4));
        Fireball.initialize(this, xOrig, yOrig);
        fireball = new Fireball(this, xOrig, yOrig);
        rocket= new Rocket(this);
        fireball.addFireball(xOrig,yOrig);
        x = xOrig;
        y = yOrig;
        cityX = (screenWidth/2) - 170;
        cityY = screenHeight - 120;
        float slingDistance = (screenHeight - Fireball.getWidth()*2) - (yOrig);
        length = slingDistance / 10;
        city = new City(this, cityX, cityY);
        explosion = new Explosion(this);
        rocket.createRockets();
        level = 2;
        drawables.add(fireball);
        drawables.add(rocket);
        drawables.add(city);
        drawables.add(explosion);
        thread = new MainThread(getHolder(), this, drawables);
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
             city.draw(canvas);
             if(times*40 < screenWidth)
             {
                 explosion.setCoordinates(times * 40, screenHeight - (city.getHeight() - 40));
                 explosion.draw(canvas);
                 times++;
             }
             else if(multiplyBy*40 < screenWidth)
             {
                 explosion.setCoordinates(multiplyBy * 40, screenHeight - (city.getHeight()/2));
                 explosion.draw(canvas);
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
            //paint.setStyle(Style.FILL);
            //paint.setColor(0x770000ff);
            //speed += acceleration;
            //xVelocity = speed * xScale;
            //yVelocity = speed * yScale;
            //x -= xVelocity;
            //y -= yVelocity;

            //Draw the Fireballs
            fireball.checkFor0Fireballs();
            fireball.checkForFireBallRemoval();
            fireball.checkForFireballCreation(down);
            fireball.addVeloc();
            rocket.move();

             if(level == 1)
             {
                 int amount = rocket.checkRocketAmount();
                 rocket.checkForSpeed(amount);
             }
         }

            //Check to see if explosion animation needs to be played.
            if(exploded)
            {
                    if(prevSec == 0)
                    {
                        prevSec = System.currentTimeMillis();
                        explosion.setCoordinates(explodeCoords[0]-25, explodeCoords[1]-25);
                        explosion.setIsActive(true);
                    }
                    else
                    {
                        nowSec = System.currentTimeMillis();
                        if(nowSec - prevSec > 100)
                        {
                            exploded = false;
                            prevSec = 0;
                        }
                        else
                        {
                            explosion.setCoordinates(explodeCoords[0]-50, explodeCoords[1]-50);
                            explosion.setIsActive(true);
                        }

                    }
                if(cityHits == 10)
                {
                    gameOver = true;
                }
            }
         //Redraw the whole canvas.
         draw.drawToCanvas(drawables, canvas);

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
        return city;
    }

}