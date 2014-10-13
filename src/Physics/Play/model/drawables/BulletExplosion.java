package Physics.Play.model.drawables;

import Physics.Play.bitmaps.BulletExplosionBitmaps;
import Physics.Play.core.MainGameView;
import Physics.Play.helpers.SerializableTimer;
import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class BulletExplosion extends Drawable implements Serializable {

    private TimerTask timerTask;
    private int imageIndex = 0;

    public BulletExplosion(MainGameView g) {
        super();
        setWidth(BulletExplosionBitmaps.getImage(0).getWidth());
        setHeight(BulletExplosionBitmaps.getImage(0).getHeight());
        startAnimation();
    }

    public BulletExplosion(MainGameView g, float x, float y) {
        super();
        startAnimation();
        setX(x);
        setY(y);
        setWidth(BulletExplosionBitmaps.getImage(0).getWidth());
        setHeight(BulletExplosionBitmaps.getImage(0).getHeight());
    }

    @Override
    public Bitmap getImage() {
        return BulletExplosionBitmaps.getImage(imageIndex);
    }


    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public void startAnimation(){
        timerTask = new SerializableTimer(this);
        new Timer().schedule(timerTask, 30L);
    }

    public void switchImage(){
        if(imageIndex < 15)
        {
            imageIndex++;
            setWidth(BulletExplosionBitmaps.getImage(imageIndex).getWidth());
            setHeight(BulletExplosionBitmaps.getImage(imageIndex).getHeight());
            startAnimation();
        }
        else
        {
            setIsActive(false);
        }
    }
}
