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


public class Fireball extends Drawable {

    private Bitmap[] imgFireball = new Bitmap[12];
    private float xVeloc, yVeloc;
    private TimerTask timerTask;
    private long delay = 50L;
    private long replay = 50L;
    private int imageIndex;
    private float xOrigin, yOrigin;
    public boolean logEnabled = true;


    public Fireball(MainGamePanel g, float scrWidth, float scrHeight){
        super();
        imgFireball[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1);
        imgFireball[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1a);
        imgFireball[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1b);
        imgFireball[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2);
        imgFireball[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2a);
        imgFireball[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2b);
        imgFireball[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3);
        imgFireball[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3a);
        imgFireball[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3b);
        imgFireball[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4);
        imgFireball[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4a);
        imgFireball[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4b);
        setWidth(imgFireball[0].getWidth());
        setHeight(imgFireball[0].getHeight());
        setImage(imgFireball[0]);
        xOrigin = (scrWidth / 2) - (imgFireball[0].getWidth() / 2);
        yOrigin = (scrHeight - (scrHeight / 4));
        setX(xOrigin);
        setY(yOrigin);
        setTimerTask();
    }

    public static void initializeStaticMembers(float screenWidth, float screenHeight, MainGamePanel g) {
    }

    private void setTimerTask(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                setImage();
            }
        } ;
        new Timer().scheduleAtFixedRate(timerTask, delay, replay);
    }

    private void setImage(){

        if(imageIndex == 11)
            imageIndex = 0;
        else
            imageIndex++;

        super.setImage(imgFireball[imageIndex]);

    }


    public float getVelocityX() {
        return xVeloc;
    }

    public float setVelocityX(float v) {
        return xVeloc = v;
    }

    public float getVelocityY() {
        return yVeloc;
    }

    public float setVelocityY(float v) {
        return yVeloc = v;
    }

    public float getOriginX() {
        return xOrigin;
    }

    public void setOriginX(float o) {
        xOrigin = o;
    }

    public float getOriginY() {
        return yOrigin;
    }

    public void setOriginY(float o) {
        yOrigin = o;
    }

    public void log(String string) {
        if(logEnabled)
            Log.i("Fireball - ", string);
    }

}
