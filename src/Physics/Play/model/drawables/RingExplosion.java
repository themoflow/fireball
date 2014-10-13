package Physics.Play.model.drawables;

import Physics.Play.bitmaps.RingExplosionBitmaps;
import Physics.Play.core.MainGameView;
import Physics.Play.helpers.SerializableTimer;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 10/7/14.
 */
public class RingExplosion extends Drawable {

    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = false;

    public RingExplosion(MainGameView g, float x, float y) {
        super();
        setWidth(RingExplosionBitmaps.getImage(0).getWidth());
        setHeight(RingExplosionBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
        startAnimation();
    }

    @Override
    public Bitmap getImage() {
        return RingExplosionBitmaps.getImage(imageIndex);
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public void startAnimation(){
        timerTask = new SerializableTimer(this);
        new Timer().schedule(timerTask, 75L);
    }

    public void switchImage(){
        if(imageIndex < RingExplosionBitmaps.getSize() - 1)
        {
            imageIndex++;
            log("imageIndex = " + imageIndex);
            setWidth(RingExplosionBitmaps.getImage(imageIndex).getWidth());
            setHeight(RingExplosionBitmaps.getImage(imageIndex).getHeight());
            startAnimation();
        }
        else
        {
            setIsActive(false);
        }
    }

    public void log(String string) {
        if(logEnabled)
            Log.i("RingExplosion.java :::: ", string);
    }
}
