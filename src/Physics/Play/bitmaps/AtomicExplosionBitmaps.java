package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public final class AtomicExplosionBitmaps {

    private static Bitmap[] atomicExplosions;
    private static int size;

    public static void initialize(MainGameView g) {
        atomicExplosions = new Bitmap[27];
        size = atomicExplosions.length;
        atomicExplosions[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_1);
        atomicExplosions[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2);
        atomicExplosions[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_3);
        atomicExplosions[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_4);
        atomicExplosions[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_5);
        atomicExplosions[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_6);
        atomicExplosions[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_7);
        atomicExplosions[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_8);
        atomicExplosions[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_9);
        atomicExplosions[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_10);
        atomicExplosions[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_11);
        atomicExplosions[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_12);
        atomicExplosions[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_13);
        atomicExplosions[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_14);
        atomicExplosions[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_15);
        atomicExplosions[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_16);
        atomicExplosions[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_17);
        atomicExplosions[17] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_18);
        atomicExplosions[18] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_19);
        atomicExplosions[19] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_20);
        atomicExplosions[20] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_21);
        atomicExplosions[21] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_22);
        atomicExplosions[22] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_23);
        atomicExplosions[23] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_24);
        atomicExplosions[24] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_25);
        atomicExplosions[25] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_26);
        atomicExplosions[26] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_27);
    }

    public static Bitmap getImage(int index) {
        return atomicExplosions[index];
    }

    public static int getSize() {
        return size;
    }
}
