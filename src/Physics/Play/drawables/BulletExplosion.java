package Physics.Play.drawables;

import Physics.Play.bitmaps.BulletExplosionBitmaps;
import Physics.Play.serializables.SerializableTimerTask;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class BulletExplosion extends Drawable implements Serializable {

    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = false;

    public BulletExplosion(MainGameView g) {
        super();
        setWidth(BulletExplosionBitmaps.getImage(0).getWidth());
        setHeight(BulletExplosionBitmaps.getImage(0).getHeight());
        startAnimation();
    }

    public BulletExplosion(MainGameView g, float x, float y) {
        super();
        startAnimation();
        setX(x);
        setY(y);
        setWidth(BulletExplosionBitmaps.getImage(0).getWidth());
        setHeight(BulletExplosionBitmaps.getImage(0).getHeight());
    }

    @Override
    public Bitmap getImage() {
        return BulletExplosionBitmaps.getImage(imageIndex);
    }


    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public void startAnimation(){
        timerTask = new SerializableTimerTask(this);
        Timer timer = new Timer();
        timer.schedule(timerTask, 30L);
    }

    public void switchImage(){
        log("switchImage() called");
        if(imageIndex < 15)
        {
            imageIndex++;
            setWidth(BulletExplosionBitmaps.getImage(imageIndex).getWidth());
            setHeight(BulletExplosionBitmaps.getImage(imageIndex).getHeight());
            startAnimation();
        }
        else
        {
            setIsActive(false);
        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: BulletExplosion.java - ", print + " ::::");
    }
}
