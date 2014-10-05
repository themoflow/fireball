package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/24/14.
 */
public class GreenDot extends Drawable {

    private float angleX, angleY;
    private float originX, originY;

    public GreenDot(MainGamePanel g, float x, float y) {
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.green_dot));
        setWidth(BitmapFactory.decodeResource(g.getResources(), R.drawable.green_dot).getWidth());
        setHeight(BitmapFactory.decodeResource(g.getResources(), R.drawable.green_dot).getHeight());
        setX(x);
        setY(y);
        originX = x;
        originY = y;
        angleX = x;
        angleY = y;

    }

    public static void initializeStaticMembers(MainGamePanel g) {

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

    public void setOriginX(float x) {
         originX = x;
    }

    public float getOriginX() {
        return originX;
    }

    public void setOriginY(float y) {
        originY = y;
    }

    public float getOriginY() {
        return originY;
    }
}
