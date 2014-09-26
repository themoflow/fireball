package Physics.Play.drawables;

import Physics.Play.core.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Random;

public class Rocket extends Drawable {

    private static float width, height, scrWidth, scrHeight;
    private int add = 0;
    private Random rand = new Random();
    private boolean hasRobot = true;
    private Robot robot;
    private boolean destroyed = false;

    public Rocket(MainGamePanel g, float y) {
        super();
        Bitmap image = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket);
        width = image.getWidth();
        height = image.getHeight();
        setX(generateRandomNumber());
        setY(y);
        setImage(image);
        add = 1;
    }

    public static void initializeStaticMembers(MainGamePanel g, float sw, float sh) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket).getHeight();
        scrWidth = sw;
        scrHeight = sh;
    }

    private float generateRandomNumber() {
        return (float)rand.nextInt((int)(scrWidth-getWidth()));
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
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
