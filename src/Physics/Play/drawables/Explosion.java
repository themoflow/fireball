package Physics.Play.drawables;

import Physics.Play.main.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Explosion extends Drawable {

    private boolean isActive = false;

    public Explosion(MainGamePanel g) {
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.explosion));
    }

    public Bitmap getImg() {
        return getImage();
    }

    public void draw(Canvas c) {
        c.drawBitmap(getImage(), getx(), gety(),null);
    }

    public void setCoordinates(float x, float y) {
        setx(x);
        sety(y);
    }

    public void setIsActive(boolean b) {

        super.setIsActive(b);
    }

    public boolean isActive() {

        return super.isActive();
    }
}
