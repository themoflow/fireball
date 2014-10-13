package Physics.Play.drawables;

import Physics.Play.bitmaps.RocketBitmaps;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;

import java.util.Random;

public class Rocket extends Drawable {

    private int add = 2;
    private Random rand = new Random();
    private int imageIndex = 0;

    public Rocket(MainGameView g, float y, float scrWidth) {
        super();
        setWidth(RocketBitmaps.getImage(0).getWidth());
        setHeight(RocketBitmaps.getImage(0).getHeight());
        setX(generateRandomNumber(scrWidth));
        setY(y);
    }

    @Override
    public Bitmap getImage() {
        return RocketBitmaps.getImage(imageIndex);
    }

    private float generateRandomNumber(float scrWidth) {
        return (float)rand.nextInt((int)(scrWidth - getWidth()));
    }

    public float getDistanceIncrement() {
        return add;
    }

}
