package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 10/3/14.
 */
public class Shield extends Drawable {

    private static int hits = 0;
    private static boolean firstShield = true;
    private TimerTask timerTask;
    private Bitmap[] bitmaps = new Bitmap[7];
    private int imageIndex = 0;

    public Shield(MainGamePanel g, float x, float y){
        super();
        if(firstShield) {
            setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_perimeter));
            setWidth(BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_perimeter).getWidth());
            setHeight(BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_perimeter).getHeight());
            firstShield = false;
        }
        else if(hits <= 5) {
            bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_green_1);
            bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_green_2);
            bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_green_3);
            bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_green_4);
            bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_green_5);
            bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_green_6);
            bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_green_7);
            setImage(bitmaps[0]);
            setWidth(bitmaps[0].getWidth());
            setHeight(bitmaps[0].getHeight());
            startAnimation();
        }
        else if(hits <= 10) {
            bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_yellow_1);
            bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_yellow_2);
            bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_yellow_3);
            bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_yellow_4);
            bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_yellow_5);
            bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_yellow_6);
            bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_yellow_7);
            setImage(bitmaps[0]);
            setWidth(bitmaps[0].getWidth());
            setHeight(bitmaps[0].getHeight());
            startAnimation();
        }
        else {
            bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_red_1);
            bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_red_2);
            bitmaps[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_red_3);
            bitmaps[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_red_4);
            bitmaps[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_red_5);
            bitmaps[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_red_6);
            bitmaps[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_red_7);
            setImage(bitmaps[0]);
            setWidth(bitmaps[0].getWidth());
            setHeight(bitmaps[0].getHeight());
            startAnimation();
        }
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {
    }

    public static void addHit(){
        hits++;
    }

    public static int getHits() {
        return hits;
    }

    public static void setHits(int h) {
         hits = h;
    }

    private void startAnimation() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                switchImage();
            }
        } ;
        new Timer().schedule(timerTask, 20L);
    }

    public void switchImage() {
        if(imageIndex < (bitmaps.length-1))
        {
            imageIndex++;
            super.setImage(bitmaps[imageIndex]);
            startAnimation();
        }
        else
        {
            setIsActive(false);
        }

    }
}
