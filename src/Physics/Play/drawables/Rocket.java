package Physics.Play.drawables;

import Physics.Play.main.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rocket extends Drawable {

    private static float width, height, scrWidth, scrHeight;
    private int add = 50;
    private Random rand = new Random();

    public Rocket(MainGamePanel g, float y) {
        super();
        Bitmap image = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket);
        width = image.getWidth();
        height = image.getHeight();
        setX(generateRandomNumber());
        setY(y);
        setImage(image);
        add = 1;
    }

    public static void initializeStaticMembers(MainGamePanel g, float sw, float sh) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket).getHeight();
        scrWidth = sw;
        scrHeight = sh;
    }

    private float generateRandomNumber() {
        return (float)rand.nextInt((int)(scrWidth-getWidth()));
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }

    public float getDistanceIncrement() {
        return add;
    }

}
