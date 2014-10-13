package Physics.Play.drawables;

import Physics.Play.bitmaps.RobotExplosionBitmaps;
import Physics.Play.views.MainGameView;
import Physics.Play.logic.SerializableTimer;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


public class RobotExplosion extends Drawable {

    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = false;

    public RobotExplosion(MainGameView g, float x, float y) {
        super();
        setWidth(RobotExplosionBitmaps.getImage(0).getWidth());
        setHeight(RobotExplosionBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
        startAnimation();
    }

    @Override
    public Bitmap getImage() {
        log("getImage() imageIndex = " + imageIndex);
        return RobotExplosionBitmaps.getImage(imageIndex);
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
        if(imageIndex < RobotExplosionBitmaps.getSize()-1)
        {
            imageIndex++;
            setWidth(RobotExplosionBitmaps.getImage(imageIndex).getWidth());
            setHeight(RobotExplosionBitmaps.getImage(imageIndex).getHeight());
            startAnimation();
        }
        else
        {
            setIsActive(false);
        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: RobotExplosionManager.java - ", print + " ::::");
    }
}
