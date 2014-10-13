package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class RobotBitmaps {

    private static Bitmap[] robots;
    private static int size;

    public static void initialize(MainGameView g) {
        robots = new Bitmap[2];
        size = robots.length;
        robots[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_sitting);
        robots[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_standing);
    }

    public static Bitmap getImage(int index) {
        return robots[index];
    }

    public static int getSize() {
        return size;
    }
}
