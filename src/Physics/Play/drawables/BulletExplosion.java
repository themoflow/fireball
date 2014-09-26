package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class BulletExplosion extends Drawable {
    private static float width, height;

    public BulletExplosion(MainGamePanel g) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion));
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
}
