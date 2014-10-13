package Physics.Play.model.drawables;

import Physics.Play.bitmaps.FireballBitmaps;
import Physics.Play.core.MainGameView;
import Physics.Play.helpers.SerializableTimer;
import android.graphics.Bitmap;
import android.util.Log;
import java.util.Timer;

public class Fireball extends Drawable {

    private float xVeloc, yVeloc;
    private SerializableTimer timerTask;
    private long delay = 50L;
    private long replay = 50L;
    private int imageIndex;
    private float xOrigin, yOrigin;
    public boolean logEnabled = true;


    public Fireball(MainGameView g, float scrWidth, float scrHeight){
        super();
        setWidth(FireballBitmaps.getImage(0).getWidth());
        setHeight(FireballBitmaps.getImage(0).getHeight());
        xOrigin = (scrWidth / 2) - (FireballBitmaps.getImage(0).getWidth() / 2);
        yOrigin = (scrHeight - (scrHeight / 4));
        setX(xOrigin);
        setY(yOrigin);
        startAnimation();
    }

    public void startAnimation(){
        timerTask = new SerializableTimer(this);
        new Timer().scheduleAtFixedRate(timerTask, delay, replay);
    }

    @Override
    public Bitmap getImage() {
        return FireballBitmaps.getImage(imageIndex);
    }

    @Override
    public void switchImage(){
        if(imageIndex == FireballBitmaps.getSize()-1)
            imageIndex = 0;
        else
            imageIndex++;
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
