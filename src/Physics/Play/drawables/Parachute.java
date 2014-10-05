package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 9/28/14.
 */
public class Parachute extends Drawable {

    private List<Bitmap> bitmaps = new ArrayList();
    private boolean isOpening = false;
    private Robot robot;
    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = false;

    public Parachute(MainGamePanel g) {
        super();
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_1));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_2));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_3));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_open));
        setWidth(bitmaps.get(0).getWidth());
        setHeight(bitmaps.get(0).getHeight());
        setImage(bitmaps.get(0));
        super.setIsActive(false);
    }

    public static void initializeStaticMembers(MainGamePanel g) {

    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
        setTimerTask();
    }

    public boolean isActive() {
        return super.isActive();
    }

    public boolean isOpening() {
        return isOpening;
    }

    public void setIsOpening(boolean o) {
        isOpening = o;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot r) {
        robot = r;
    }

    private void setTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                switchImage();
            }
        } ;
        new Timer().schedule(timerTask, 110L);
    }

    private void switchImage() {
        if(imageIndex < 3)
        {
            imageIndex++;
            setImage(bitmaps.get(imageIndex));
            setWidth(bitmaps.get(imageIndex).getWidth());
            setHeight(bitmaps.get(imageIndex).getHeight());
            float parachuteCenterX = getWidth() / 2;
            float robotCenterX = robot.getWidth() / 2;
            log("switchImage, robot getX() = " + robot.getX());
            log("switchImage, robot center x = " + (robot.getX() +  robotCenterX));
            log("switchImage, parachurte center x = " + parachuteCenterX);
            setX((robot.getX() +  robotCenterX) - (parachuteCenterX));
            setTimerTask();
        }
    }

    private void log(String print){

        if(logEnabled)
            Log.i("Parachute - ", print);
    }

}
