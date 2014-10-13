package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class BulletBitmaps {

    private static Bitmap[] bullets;

    public static void initialize(MainGameView g) {
        bullets = new Bitmap[1];
        bullets[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet);
    }

    public static Bitmap getImage(int index) {
        return bullets[index];
    }
}
