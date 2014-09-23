package Physics.Play.drawables;

import Physics.Play.main.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
    private List<Fireball> fireballs = new ArrayList<Fireball>();
    public static int amountOfFireballs = 0;
    private MainGamePanel gamePanel;
    private static float xOrigin, yOrigin, width, height;
    public boolean logEnabled = true;


    public Fireball(MainGamePanel g, float x, float y){
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
        setx(x);
        sety(y);
        setImage(imgFireball[0]);
        setTimerTask();
        this.gamePanel = g;

    }

    //this is the initializer constructor. It must be called first untill i fix my bad code.
    public Fireball(MainGamePanel g){
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
        setImage(imgFireball[0]);
        this.gamePanel = g;
        log("image width = " + getWidth());
        log("image height = " + getHeight());
    }

    public static void initializeStaticMembers(float xOrig, float yOrig, MainGamePanel g) {
        xOrigin = xOrig;
        yOrigin = yOrig;
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1).getHeight();
    }
    public Bitmap getImage(){
        return super.getImage();
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

    public void setImage(Bitmap image) {
        super.setImage(image);
    }

    public  static float getHeight() {
        return height;
    }

    public static float getWidth() {
        return width;
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

    public static float getOriginX() {
        return xOrigin;
    }

    public static void setOriginX(float o) {
        xOrigin = o;
    }

    public static float getOriginY() {
        return yOrigin;
    }

    public static void setOriginY(float o) {
        yOrigin = o;
    }

    public void log(String string) {
        if(logEnabled)
            Log.i("Fireball - ", string);
    }

}
