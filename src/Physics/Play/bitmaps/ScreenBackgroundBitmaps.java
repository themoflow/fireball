package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class ScreenBackgroundBitmaps {

    private static Bitmap[] screenBackgrounds;
    private static int size;

    public static void initialize(MainGameView g) {
        screenBackgrounds = new Bitmap[1];
        size = screenBackgrounds.length;
        screenBackgrounds[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.screen_background);
    }

    public static Bitmap getImage(int index) {
        return screenBackgrounds[index];
    }

    public static int getSize() {
        return size;
    }
}
