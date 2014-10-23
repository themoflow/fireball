package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.activities.MainGameActivity;
import Physics.Play.bitmaps.FireballBitmaps;
import Physics.Play.serializables.SerializableThread;
import Physics.Play.serializables.SerializableTimerTask;
import Physics.Play.views.MainGameView;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import Physics.Play.serializables.SerializableTimer;
import android.widget.ImageView;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Fireball extends Drawable {

    private float xVeloc, yVeloc;
    private SerializableTimerTask timerTask;
    private SerializableTimer timer;
    private int imageIndex;
    private float xOrigin, yOrigin;
    public boolean logEnabled = true;
    private int count;
    public static int amount = 0;
    private boolean endTimer = false;
    private SerializableThread animationThread;
    //public static List<String> stopAnimation = new ArrayList();
    public static HashMap<Integer, String> stopAnimation = new HashMap();

    public Fireball(MainGameView g, float scrWidth, float scrHeight, Activity a){
        super();
        amount++;
        count = amount;
        setWidth(FireballBitmaps.getImage(0).getWidth());
        setHeight(FireballBitmaps.getImage(0).getHeight());
        xOrigin = (scrWidth / 2) - (FireballBitmaps.getImage(0).getWidth() / 2);
        yOrigin = (scrHeight - (scrHeight / 4));
        setX(xOrigin);
        setY(yOrigin);
        startAnimation();
    }

    public void startAnimation(){
        log("stopAnimation.size() = "+ stopAnimation.size());
        stopAnimation.put(count,"run");
        animationThread = new SerializableThread(this);
        animationThread.start();
        /*va = new ValueAnimator();
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setFloatValues(0, FireballBitmaps.getSize() - 1);
        va.start();
        log("va = " + va.getAnimatedValue());*/

       /* ImageView imageView = (ImageView) mga.findViewById(R.id.fireball_animation_image_view);
        imageView.setBackgroundResource(R.drawable.fireball_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) imageView.getBackground();
        frameAnimation.start();*/

        /*timerTask = new SerializableTimerTask(this);
        timer = new SerializableTimer();
        timer.scheduleAtFixedRate(timerTask, 50L, 50L);*/

        /*runnable = new Runnable() {
            @Override
            public void run() {
                switchImage();
                handler.postDelayed(this, 50);
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 50);*/
    }

    @Override
    public Bitmap getImage() {
        return FireballBitmaps.getImage(imageIndex);
    }

    @Override
    public void switchImage(){
        log("switchImage(), count = " + (count));
        if(imageIndex >= FireballBitmaps.getSize()-1)
            imageIndex = 0;
        else
            imageIndex++;
    }

    public void cancelTimer() {
        stopAnimation.put(count, "stop");
        log("cancelTimer(), count  = " + (count));
        log("cancelTimer(), runAnimation.get()  = " + stopAnimation.get(count));
        /*try
        {

            animationThread.join();
        }
        catch(InterruptedException e)
        {
            log("PROBLEM -> exception thrown during cancelTimer(), msg = " + e.getMessage());
        }*/
        //log("cancelTimer() called, count = " + count + ", runAnimation = " + animationThread.getRun());

        /*endTimer = true;
        timerTask.cancel();
        timer.cancel();
        timer.purge();*/

    }

    @Override
    public int getCount() {
        return count;
    }

    public boolean isTimerOver() {
        return endTimer;
    }

    public void setEndTimer(boolean et) {
         endTimer = et;
    }

    public float getVelocityX() {
        return xVeloc;
    }

    public void setVelocityX(float v) {
         xVeloc = v;
    }

    public float getVelocityY() {
        return yVeloc;
    }

    public float setVelocityY(float v) {
        return yVeloc = v;
    }

    public float getOriginX() {
        return xOrigin;
    }

    public void setOriginX(float o) {
        xOrigin = o;
    }

    public float getOriginY() {
        return yOrigin;
    }

    public void setOriginY(float o) {
        yOrigin = o;
    }

    public void log(String string) {
        if(logEnabled)
            Log.i(":::: Fireball.java -> ", string + " ::::");
    }

}
