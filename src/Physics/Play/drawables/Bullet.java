package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class Bullet extends Drawable {

    private static float width, height;

    public Bullet(MainGamePanel g) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet));
    }

    public Bullet(MainGamePanel g, float x, float y) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet));
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet).getHeight();
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
}
