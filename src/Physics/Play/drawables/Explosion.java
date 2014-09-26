package Physics.Play.drawables;

import Physics.Play.core.MainGamePanel;
import Physics.Play.R;
import android.graphics.BitmapFactory;


public class Explosion extends Drawable {

    private static float width, height;
    private double startTime;

    public Explosion(MainGamePanel g) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.explosion));
        startTime = System.currentTimeMillis();
    }

    public Explosion(MainGamePanel g, float x, float y) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.explosion));
        startTime = System.currentTimeMillis();
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.explosion).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.explosion).getHeight();
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
