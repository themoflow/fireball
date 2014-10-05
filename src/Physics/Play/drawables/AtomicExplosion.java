package Physics.Play.drawables;

import Physics.Play.core.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class AtomicExplosion extends Drawable {

    private double startTime;
    private Bitmap[] bitmaps;
    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean hasExploded = false;
    private boolean logEnabled = true;

    public AtomicExplosion(MainGamePanel g) {
        super();
        bitmaps = new Bitmap[27];
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_1);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2);
        bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_3);
        bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_4);
        bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_5);
        bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_6);
        bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_7);
        bitmaps[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_8);
        bitmaps[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_9);
        bitmaps[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_10);
        bitmaps[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_11);
        bitmaps[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_12);
        bitmaps[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_13);
        bitmaps[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_14);
        bitmaps[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_15);
        bitmaps[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_16);
        bitmaps[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_17);
        bitmaps[17] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_18);
        bitmaps[18] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_19);
        bitmaps[19] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_20);
        bitmaps[20] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_21);
        bitmaps[21] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_22);
        bitmaps[22] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_23);
        bitmaps[23] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_24);
        bitmaps[24] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_25);
        bitmaps[25] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_26);
        bitmaps[26] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_27);
        setImage(bitmaps[0]);
        setWidth(bitmaps[0].getWidth());
        setHeight(bitmaps[0].getHeight());
        startAnimation();
    }


    public AtomicExplosion(MainGamePanel g, float x, float y) {
        super();
        bitmaps = new Bitmap[27];
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_1);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2);
        bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_3);
        bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_4);
        bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_5);
        bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_6);
        bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_7);
        bitmaps[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_8);
        bitmaps[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_9);
        bitmaps[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_10);
        bitmaps[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_11);
        bitmaps[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_12);
        bitmaps[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_13);
        bitmaps[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_14);
        bitmaps[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_15);
        bitmaps[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_16);
        bitmaps[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_17);
        bitmaps[17] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_18);
        bitmaps[18] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_19);
        bitmaps[19] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_20);
        bitmaps[20] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_21);
        bitmaps[21] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_22);
        bitmaps[22] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_23);
        bitmaps[23] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_24);
        bitmaps[24] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_25);
        bitmaps[25] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_26);
        bitmaps[26] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_27);
        setImage(bitmaps[0]);
        setX(x);
        setY(y);
        setWidth(bitmaps[0].getWidth());
        setHeight(bitmaps[0].getHeight());
        startAnimation();
    }

    public static void initializeStaticMembers(MainGamePanel g) {

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

    public boolean hasExploded() {
        return hasExploded;
    }

    private void startAnimation(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                switchImage();
            }
        } ;
        new Timer().schedule(timerTask, 150L);

    }

    private void switchImage(){
            if (imageIndex < 26) {
                imageIndex++;
                setImage(bitmaps[imageIndex]);
                setWidth(bitmaps[imageIndex].getWidth());
                setHeight(bitmaps[imageIndex].getHeight());
                startAnimation();
            } else {
                hasExploded = true;
            }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("CityExplosion.class - ", print);
    }
}
