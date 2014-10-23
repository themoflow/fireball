package Physics.Play.drawables;

import Physics.Play.bitmaps.AtomicExplosionBitmaps;
import Physics.Play.serializables.SerializableTimerTask;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class AtomicExplosion extends Drawable {


    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = false;

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
        timerTask = new SerializableTimerTask(this);
        Timer timer = new Timer();
        timer.schedule(timerTask, 150L);
    }

    public void switchImage(){
        log("switchImage() called");
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
