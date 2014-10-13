package Physics.Play.model.drawables;

import Physics.Play.bitmaps.RocketBitmaps;
import Physics.Play.core.MainGameView;
import android.graphics.Bitmap;

import java.util.Random;

public class Rocket extends Drawable {

    private int add = 2;
    private Random rand = new Random();
    private boolean hasRobot = true;
    private boolean destroyed = false;
    private int imageIndex = 0;

    public Rocket(MainGameView g, float y, float scrWidth) {
        super();
        setWidth(RocketBitmaps.getImage(0).getWidth());
        setHeight(RocketBitmaps.getImage(0).getHeight());
        setX(generateRandomNumber(scrWidth));
        setY(y);
    }

    @Override
    public Bitmap getImage() {
        return RocketBitmaps.getImage(imageIndex);
    }

    private float generateRandomNumber(float scrWidth) {
        return (float)rand.nextInt((int)(scrWidth - getWidth()));
    }

    public float getDistanceIncrement() {
        return add;
    }

    public void setHasRobot(boolean b) {
        hasRobot = b;
    }

    public boolean hasRobot() {
        return hasRobot;
    }

    public void setDestroyed(boolean b) {
        destroyed = b;
    }

    public boolean hasBeenDestroyed() {
        return destroyed;
    }

}
