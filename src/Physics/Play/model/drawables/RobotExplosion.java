package Physics.Play.model.drawables;

import Physics.Play.bitmaps.RobotExplosionBitmaps;
import Physics.Play.core.MainGameView;
import Physics.Play.helpers.SerializableTimer;
import android.graphics.Bitmap;

import java.util.Timer;
import java.util.TimerTask;


public class RobotExplosion extends Drawable {

    private double startTime;
    private TimerTask timerTask;
    private int imageIndex = 0;

    public RobotExplosion(MainGameView g, float x, float y) {
        super();
        setWidth(RobotExplosionBitmaps.getImage(0).getWidth());
        setHeight(RobotExplosionBitmaps.getImage(0).getHeight());
        startTime = System.currentTimeMillis();
        setX(x);
        setY(y);
        startAnimation();
    }

    @Override
    public Bitmap getImage() {
        return RobotExplosionBitmaps.getImage(imageIndex);
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public void setStartTime(double s) {
        startTime = s;
    }

    public double getStartTime() {
        return startTime;
    }


    public void startAnimation(){
        timerTask = new SerializableTimer(this);
        new Timer().schedule(timerTask, 30L);
    }

    public void switchImage(){
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
}
