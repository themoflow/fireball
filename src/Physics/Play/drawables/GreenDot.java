package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/24/14.
 */
public class GreenDot extends Drawable {

    public static float width, height;
    private float angleX, angleY;
    private static float originX, originY;

    public GreenDot(MainGamePanel g, float x, float y) {
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.green_dot));
        setX(x);
        setY(y);
        originX = x;
        originY = y;
        angleX = x;
        angleY = y;
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.green_dot).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.green_dot).getHeight();
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }

    public float getAngleX() {
        return angleX;
    }

    public void setAngleX(float x) {
        angleX = x;
    }

    public float getAngleY() {
        return angleY;
    }

    public void setAngleY(float y) {
        angleY = y;
    }

    public static void setOriginX(float x) {
         originX = x;
    }

    public static float getOriginX() {
        return originX;
    }

    public static void setOriginY(float y) {
        originY = y;
    }

    public static float getOriginY() {
        return originY;
    }
}
