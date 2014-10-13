package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class RingExplosionBitmaps {

    private static Bitmap[] ringExplosions;
    private static int size;

    public static void initialize(MainGameView g) {
        ringExplosions = new Bitmap[38];
        size = ringExplosions.length;
        ringExplosions[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_1);
        ringExplosions[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_2);
        ringExplosions[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_3);
        ringExplosions[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_4);
        ringExplosions[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_5);
        ringExplosions[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_6);
        ringExplosions[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_7);
        ringExplosions[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_8);
        ringExplosions[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_9);
        ringExplosions[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_10);
        ringExplosions[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_11);
        ringExplosions[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_12);
        ringExplosions[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_13);
        ringExplosions[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_14);
        ringExplosions[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_15);
        ringExplosions[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_16);
        ringExplosions[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_17);
        ringExplosions[17] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_18);
        ringExplosions[18] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_19);
        ringExplosions[19] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_20);
        ringExplosions[20] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_21);
        ringExplosions[21] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_22);
        ringExplosions[22] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_23);
        ringExplosions[23] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_24);
        ringExplosions[24] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_25);
        ringExplosions[25] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_26);
        ringExplosions[26] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_27);
        ringExplosions[27] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_28);
        ringExplosions[28] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_29);
        ringExplosions[29] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_30);
        ringExplosions[30] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_31);
        ringExplosions[31] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_32);
        ringExplosions[32] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_33);
        ringExplosions[33] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_34);
        ringExplosions[34] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_35);
        ringExplosions[35] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_36);
        ringExplosions[36] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_37);
        ringExplosions[37] = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_38);
    }

    public static Bitmap getImage(int index) {
        return ringExplosions[index];
    }

    public static int getSize() {
        return size;
    }
}
