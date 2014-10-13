package Physics.Play.drawables;

import Physics.Play.bitmaps.CityExplosionBitmaps;
import Physics.Play.views.MainGameView;
import Physics.Play.logic.SerializableTimer;
import android.graphics.Bitmap;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class CityExplosion extends Drawable {

    private TimerTask timerTask;
    private int imageIndex = 0;
    private boolean logEnabled = false;

    public CityExplosion(MainGameView g) {
        super();
        setWidth(CityExplosionBitmaps.getImage(0).getWidth());
        setHeight(CityExplosionBitmaps.getImage(0).getHeight());
        startAnimation();
    }

    public CityExplosion(MainGameView g, float x, float y) {
        super();
        setWidth(CityExplosionBitmaps.getImage(0).getWidth());
        setHeight(CityExplosionBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
        startAnimation();
    }

    @Override
    public Bitmap getImage() {
        return CityExplosionBitmaps.getImage(imageIndex);
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public void startAnimation(){
        timerTask = new SerializableTimer(this);
        new Timer().schedule(timerTask, 150L);

    }

    public void switchImage(){
        if (imageIndex < CityExplosionBitmaps.getSize()-1) {
            imageIndex++;
            log("bitmap at index " + imageIndex + " = " + CityExplosionBitmaps.getImage(imageIndex));
            startAnimation();
        } else {
            setIsActive(false);
        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("CityExplosion.class - ", print);
    }
}
