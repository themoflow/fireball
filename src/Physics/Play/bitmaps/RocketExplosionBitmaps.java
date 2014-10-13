package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class RocketExplosionBitmaps {

    private static Bitmap[] rocketsExplosions;
    private static int size;

    public static void initialize(MainGameView g) {
        rocketsExplosions = new Bitmap[17];
        size = rocketsExplosions.length;
        rocketsExplosions[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_1);
        rocketsExplosions[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_2);
        rocketsExplosions[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_3);
        rocketsExplosions[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_4);
        rocketsExplosions[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_5);
        rocketsExplosions[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_6);
        rocketsExplosions[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_7);
        rocketsExplosions[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_8);
        rocketsExplosions[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_9);
        rocketsExplosions[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_10);
        rocketsExplosions[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_11);
        rocketsExplosions[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_12);
        rocketsExplosions[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_13);
        rocketsExplosions[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_14);
        rocketsExplosions[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_15);
        rocketsExplosions[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_16);
        rocketsExplosions[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket_explosion_17);
    }

    public static Bitmap getImage(int index) {
        return rocketsExplosions[index];
    }

    public static int getSize() {
        return size;
    }
}
