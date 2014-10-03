package Physics.Play.drawables;

import Physics.Play.core.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class CityExplosion extends Drawable {

    private static float width, height;
    private float currentWidth, currentHeight;
    private double startTime;
    private Bitmap[] bitmaps = new Bitmap[30];
    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean hasExploded = false;
    private boolean logEnabled = false;

    public CityExplosion(MainGamePanel g) {
        super();
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_1);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_2);
        bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_3);
        bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_4);
        bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_5);
        bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_6);
        bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_7);
        bitmaps[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_8);
        bitmaps[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_9);
        bitmaps[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_10);
        bitmaps[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_11);
        bitmaps[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_12);
        bitmaps[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_13);
        bitmaps[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_14);
        bitmaps[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_15);
        bitmaps[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_16);
        bitmaps[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_17);
        bitmaps[17] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_18);
        bitmaps[18] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_19);
        bitmaps[19] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_20);
        bitmaps[20] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_21);
        bitmaps[21] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_22);
        bitmaps[22] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_23);
        bitmaps[23] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_24);
        bitmaps[24] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_25);
        bitmaps[25] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_26);
        bitmaps[26] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_27);
        bitmaps[27] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_28);
        bitmaps[28] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_29);
        bitmaps[29] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_30);
        super.setImage(bitmaps[0]);
        startAnimation();
    }

    public CityExplosion(MainGamePanel g, float x, float y) {
        super();
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_1);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_2);
        bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_3);
        bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_4);
        bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_5);
        bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_6);
        bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_7);
        bitmaps[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_8);
        bitmaps[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_9);
        bitmaps[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_10);
        bitmaps[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_11);
        bitmaps[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_12);
        bitmaps[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_13);
        bitmaps[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_14);
        bitmaps[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_15);
        bitmaps[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_16);
        bitmaps[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_17);
        bitmaps[17] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_18);
        bitmaps[18] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_19);
        bitmaps[19] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_20);
        bitmaps[20] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_21);
        bitmaps[21] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_22);
        bitmaps[22] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_23);
        bitmaps[23] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_24);
        bitmaps[24] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_25);
        bitmaps[25] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_26);
        bitmaps[26] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_27);
        bitmaps[27] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_28);
        bitmaps[28] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_29);
        bitmaps[29] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_30);
        super.setImage(bitmaps[0]);
        setX(x);
        setY(y);
        startAnimation();
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_1).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_1).getHeight();
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
        if (imageIndex < 29) {
            imageIndex++;
            log("bitmap at index " + imageIndex + " = " + bitmaps[imageIndex]);
            super.setImage(bitmaps[imageIndex]);
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
