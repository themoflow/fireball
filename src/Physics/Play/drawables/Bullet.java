package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.bitmaps.BulletBitmaps;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class Bullet extends Drawable {

    private int imageIndex = 0;

    public Bullet(MainGameView g) {
        super();
        setWidth(BulletBitmaps.getImage(0).getWidth());
        setHeight(BulletBitmaps.getImage(0).getHeight());
    }

    public Bullet(MainGameView g, float x, float y) {
        super();
        setX(x);
        setY(y);
        setWidth(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet).getWidth());
        setHeight(BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet).getHeight());
    }

    @Override
    public Bitmap getImage() {
        return BulletBitmaps.getImage(imageIndex);
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }


}
