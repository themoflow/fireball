package Physics.Play;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Explosion extends Drawable {


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
}
