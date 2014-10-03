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

    private static float[] parachuteWidths = new float[4];
    private static float[] parachuteHeights = new float[4];
    private float currentWidth, currentHeight;
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
        currentWidth = bitmaps.get(0).getWidth();
        currentHeight = bitmaps.get(0).getHeight();
        super.setIsActive(false);
        setImage(bitmaps.get(0));
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        parachuteWidths[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_1).getWidth();
        parachuteHeights[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_1).getHeight();
        parachuteWidths[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_2).getWidth();
        parachuteHeights[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_2).getHeight();
        parachuteWidths[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_3).getWidth();
        parachuteHeights[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_3).getHeight();
        parachuteWidths[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_open).getWidth();
        parachuteHeights[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_open).getHeight();
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
        setTimerTask();
    }

    public boolean isActive() {
        return super.isActive();
    }

    public static float getHeight(int index){
        return parachuteHeights[index];
    }

    public static float getWidth(int index){
        return parachuteWidths[index];
    }

    public float getCurrentHeight(){
        return currentHeight;
    }

    public float getCurrentWidth(){
        return currentWidth ;
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
            super.setImage(bitmaps.get(imageIndex));
            float parachuteCenterX = Parachute.getWidth(imageIndex) / 2;
            float robotCenterX = robot.getStandingWidth() / 2;
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
