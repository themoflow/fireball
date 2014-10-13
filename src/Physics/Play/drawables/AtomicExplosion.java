package Physics.Play.drawables;

import Physics.Play.bitmaps.AtomicExplosionBitmaps;
import Physics.Play.views.MainGameView;
import Physics.Play.logic.SerializableTimer;
import android.graphics.Bitmap;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class AtomicExplosion extends Drawable {


    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = true;

    public AtomicExplosion(MainGameView g) {
        super();
        setWidth(AtomicExplosionBitmaps.getImage(0).getWidth());
        setHeight(AtomicExplosionBitmaps.getImage(0).getHeight());
        startAnimation();
    }

    public AtomicExplosion(MainGameView g, float x, float y) {
        super();
        setX(x);
        setY(y);
        setWidth(AtomicExplosionBitmaps.getImage(0).getWidth());
        setHeight(AtomicExplosionBitmaps.getImage(0).getHeight());
        startAnimation();
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public Bitmap getImage() {
        return AtomicExplosionBitmaps.getImage(imageIndex);
    }

    public void startAnimation(){
        timerTask = new SerializableTimer(this);
        new Timer().schedule(timerTask, 150L);
    }

    public void switchImage(){
            if (imageIndex < AtomicExplosionBitmaps.getSize()-1) {
                imageIndex++;
                setWidth(AtomicExplosionBitmaps.getImage(imageIndex).getWidth());
                setHeight(AtomicExplosionBitmaps.getImage(imageIndex).getHeight());
                startAnimation();
            } else {
                setIsActive(false);
            }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("AtomicExplosion.java - ", print);
    }
}
