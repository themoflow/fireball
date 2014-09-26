package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class BulletExplosion extends Drawable {
    private static float width, height;
    private double startTime;

    public BulletExplosion(MainGamePanel g) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion));
        startTime = System.currentTimeMillis();
    }

    public BulletExplosion(MainGamePanel g, float x, float y) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion));
        startTime = System.currentTimeMillis();
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion).getHeight();
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public static float getHeight(){
        return height;
    }

    public static float getWidth(){
        return width;
    }

    public void setStartTime(double s) {
        startTime = s;
    }

    public double getStartTime() {
        return startTime;
    }
}
