package Physics.Play.drawables;

import Physics.Play.bitmaps.RocketExplosionBitmaps;
import Physics.Play.views.MainGameView;
import Physics.Play.logic.SerializableTimer;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


public class RocketExplosion extends Drawable {

    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = false;

    public RocketExplosion(MainGameView g) {
        super();
        setWidth(RocketExplosionBitmaps.getImage(0).getWidth());
        setHeight(RocketExplosionBitmaps.getImage(0).getHeight());
        startAnimation();
    }

    public RocketExplosion(MainGameView g, float x, float y) {
        super();
        setWidth(RocketExplosionBitmaps.getImage(0).getWidth());
        setHeight(RocketExplosionBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
        startAnimation();
    }

    @Override
    public Bitmap getImage() {
        log("getImage() imageIndex = " + imageIndex);
        return RocketExplosionBitmaps.getImage(imageIndex);
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public void startAnimation(){
        timerTask = new SerializableTimer(this);
        new Timer().schedule(timerTask, 30L);
    }

    public void switchImage(){
        log("switchImage() imageIndex = " + imageIndex);
        if(imageIndex < RocketExplosionBitmaps.getSize()-1)
        {
            imageIndex++;
            setWidth(RocketExplosionBitmaps.getImage(imageIndex).getWidth());
            setHeight(RocketExplosionBitmaps.getImage(imageIndex).getHeight());
            startAnimation();
        }
        else
        {
            log("switchImage() setIsActive(false)");
            setIsActive(false);
        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: RocketExplosion.java - ", print);
    }
}
