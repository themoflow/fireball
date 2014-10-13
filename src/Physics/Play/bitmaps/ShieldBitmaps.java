package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class ShieldBitmaps {

    private static Bitmap[] shields;
    private static int size;

    public static void initialize(MainGameView g) {
        shields = new Bitmap[3];
        shields[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_green);
        shields[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_yellow);
        shields[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_red);
        size = shields.length;
    }

    public static Bitmap getImage(int index) {
        return shields[index];
    }

    public static int getSize() {
        return size;
    }
}
