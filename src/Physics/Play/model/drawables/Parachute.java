package Physics.Play.model.drawables;

import Physics.Play.bitmaps.ParachuteBitmaps;
import Physics.Play.core.MainGameView;
import Physics.Play.helpers.SerializableTimer;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 9/28/14.
 */
public class Parachute extends Drawable {

    private boolean isOpening = false;
    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = false;

    public Parachute(MainGameView g, float x, float y) {
        super();

        setWidth(ParachuteBitmaps.getImage(0).getWidth());
        setHeight(ParachuteBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
        startAnimation();
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    @Override
    public Bitmap getImage() {
        return ParachuteBitmaps.getImage(imageIndex);
    }

    public boolean isActive() {
        return super.isActive();
    }



    public void startAnimation() {
        timerTask = new SerializableTimer(this);
        new Timer().schedule(timerTask, 50L);
    }

    public void switchImage() {
        if(imageIndex < ParachuteBitmaps.getSize()-1)
        {
            Drawable robot = getDrawable(Robot.class);
            imageIndex++;
            setWidth(ParachuteBitmaps.getImage(imageIndex).getWidth());
            setHeight(ParachuteBitmaps.getImage(imageIndex).getHeight());
            float parachuteCenterX = getWidth() / 2;
            float robotCenterX = robot.getWidth() / 2;
            setX((robot.getX() +  robotCenterX) - (parachuteCenterX));
            startAnimation();
        }
        else
        {
            isOpening = false;
        }
    }

    private void log(String print){

        if(logEnabled)
            Log.i("Parachute - ", print);
    }

}
