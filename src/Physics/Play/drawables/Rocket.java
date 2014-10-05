package Physics.Play.drawables;

import Physics.Play.core.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Random;

public class Rocket extends Drawable {

    private int add = 2;
    private Random rand = new Random();
    private boolean hasRobot = true;
    private Robot robot;
    private boolean destroyed = false;

    public Rocket(MainGamePanel g, float y, float scrWidth) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket));
        setWidth(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket).getWidth());
        setHeight(BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket).getHeight());
        setX(generateRandomNumber(scrWidth));
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g, float sw, float sh) {

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

    public void setRobot(Robot r) {
        robot = r;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setDestroyed(boolean b) {
        destroyed = b;
    }

    public boolean hasBeenDestroyed() {
        return destroyed;
    }

}
