package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class BulletExplosion extends Drawable {
    private double startTime;
    private TimerTask timerTask;
    private int imageIndex = 0;
    private Bitmap[] bitmaps = new Bitmap[17];
    private boolean hasExploded = false;

    public BulletExplosion(MainGamePanel g) {
        super();
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_1);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_2);
        bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_3);
        bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_4);
        bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_5);
        bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_6);
        bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_7);
        bitmaps[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_8);
        bitmaps[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_9);
        bitmaps[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_10);
        bitmaps[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_11);
        bitmaps[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_12);
        bitmaps[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_13);
        bitmaps[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_14);
        bitmaps[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_15);
        bitmaps[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_16);
        bitmaps[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_17);
        setImage(bitmaps[0]);
        setWidth(bitmaps[0].getWidth());
        setHeight(bitmaps[0].getHeight());
        startAnimation();
    }

    public BulletExplosion(MainGamePanel g, float x, float y) {
        super();
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_1);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_2);
        bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_3);
        bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_4);
        bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_5);
        bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_6);
        bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_7);
        bitmaps[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_8);
        bitmaps[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_9);
        bitmaps[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_10);
        bitmaps[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_11);
        bitmaps[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_12);
        bitmaps[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_13);
        bitmaps[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_14);
        bitmaps[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_15);
        bitmaps[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_16);
        bitmaps[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_17);
        setImage(bitmaps[0]);
        startAnimation();
        setX(x);
        setY(y);
        setWidth(bitmaps[0].getWidth());
        setHeight(bitmaps[0].getHeight());
    }

    public static void initializeStaticMembers(MainGamePanel g) {

    }

    public boolean hasExploded() {
        return hasExploded;
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

    private void startAnimation(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                switchImage();
            }
        } ;
        new Timer().schedule(timerTask, 30L);
    }

    private void switchImage(){
        if(imageIndex < 15)
        {
            imageIndex++;
            setWidth(bitmaps[imageIndex].getWidth());
            setHeight(bitmaps[imageIndex].getHeight());
            setImage(bitmaps[imageIndex]);
            startAnimation();
        }
        else
        {
            hasExploded = true;
        }
    }
}
