package Physics.Play.drawables;

import Physics.Play.core.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class RocketExplosion extends Drawable {

    private static float width, height;
    private float currentWidth, currentHeight;
    private double startTime;
    private List<Bitmap> bitmaps = new ArrayList();
    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean hasExploded = false;
    private boolean logEnabled = false;

    public RocketExplosion(MainGamePanel g) {
        super();
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_1));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_2));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_3));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_4));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_5));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_6));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_7));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_8));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_9));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_10));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_11));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_12));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_13));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_14));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_15));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_16));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_17));
        setImage(bitmaps.get(0));
        startTime = System.currentTimeMillis();
        setTimerTask();
    }

    public RocketExplosion(MainGamePanel g, float x, float y) {
        super();
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_1));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_2));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_3));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_4));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_5));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_6));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_7));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_8));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_9));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_10));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_11));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_12));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_13));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_14));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_15));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_16));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_17));
        setImage(bitmaps.get(0));
        startTime = System.currentTimeMillis();
        setX(x);
        setY(y);
        setTimerTask();
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_1).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_1).getHeight();
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public static float getHeight(){
        return height;
    }

    public static float getWidth(){
        return width;
    }

    public void setStartTime(double s) {
        startTime = s;
    }

    public double getStartTime() {
        return startTime;
    }

    public float getCurentWidth() {
        return currentWidth;
    }

    public float getCurentHeight() {
        return currentHeight;
    }

    public void setCurrentWidth(float c) {
        currentWidth = c;
    }

    public void setCurrentHeight(float c) {
        currentHeight = c;
    }

    public boolean hasExploded() {
        return hasExploded;
    }

    private void setTimerTask(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                setImage();
            }
        } ;
        new Timer().schedule(timerTask, 30L);
    }

    private void setImage(){
        if(imageIndex < 15)
        {
            imageIndex++;
            super.setImage(bitmaps.get(imageIndex));
            setTimerTask();
        }
        else
        {
            hasExploded = true;
        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("RocketExplosion - ", print);
    }
}
