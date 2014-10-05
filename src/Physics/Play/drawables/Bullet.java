package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class Bullet extends Drawable {

    public Bullet(MainGamePanel g) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet));
        setWidth(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet).getWidth());
        setHeight(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet).getHeight());
    }

    public Bullet(MainGamePanel g, float x, float y) {
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet));
        setX(x);
        setY(y);
        setWidth(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet).getWidth());
        setHeight(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet).getHeight());
    }

    public static void initializeStaticMembers(MainGamePanel g) {

    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }


}
