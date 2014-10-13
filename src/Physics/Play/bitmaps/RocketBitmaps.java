package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class RocketBitmaps {

    private static Bitmap[] rockets;
    private static int size;

    public static void initialize(MainGameView g) {
        rockets = new Bitmap[1];
        size = rockets.length;
        rockets[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket);
    }

    public static Bitmap getImage(int index) {
        return rockets[index];
    }

    public static int getSize() {
        return size;
    }
}
