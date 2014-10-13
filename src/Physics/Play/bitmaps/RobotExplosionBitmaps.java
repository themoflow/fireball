package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class RobotExplosionBitmaps {

    private static Bitmap[] robotExplosions;
    private static int size;

    public static void initialize(MainGameView g) {
        robotExplosions = new Bitmap[17];
        size = robotExplosions.length;
        robotExplosions[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_1);
        robotExplosions[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_2);
        robotExplosions[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_3);
        robotExplosions[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_4);
        robotExplosions[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_5);
        robotExplosions[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_6);
        robotExplosions[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_7);
        robotExplosions[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_8);
        robotExplosions[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_9);
        robotExplosions[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_1);
        robotExplosions[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_11);
        robotExplosions[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_12);
        robotExplosions[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_13);
        robotExplosions[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_14);
        robotExplosions[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_15);
        robotExplosions[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_16);
        robotExplosions[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_explosion_17);
    }

    public static Bitmap getImage(int index) {
        return robotExplosions[index];
    }

    public static int getSize() {
        return size;
    }
}
