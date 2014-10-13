package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class CityExplosionBitmaps {

    private static Bitmap[] cityExplosions;
    private static int size;

    public static void initialize(MainGameView g) {
        cityExplosions = new Bitmap[30];
        size = cityExplosions.length;
        cityExplosions[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_1);
        cityExplosions[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_2);
        cityExplosions[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_3);
        cityExplosions[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_4);
        cityExplosions[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_5);
        cityExplosions[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_6);
        cityExplosions[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_7);
        cityExplosions[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_8);
        cityExplosions[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_9);
        cityExplosions[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_10);
        cityExplosions[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_11);
        cityExplosions[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_12);
        cityExplosions[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_13);
        cityExplosions[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_14);
        cityExplosions[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_15);
        cityExplosions[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_16);
        cityExplosions[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_17);
        cityExplosions[17] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_18);
        cityExplosions[18] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_19);
        cityExplosions[19] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_20);
        cityExplosions[20] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_21);
        cityExplosions[21] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_22);
        cityExplosions[22] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_23);
        cityExplosions[23] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_24);
        cityExplosions[24] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_25);
        cityExplosions[25] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_26);
        cityExplosions[26] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_27);
        cityExplosions[27] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_28);
        cityExplosions[28] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_29);
        cityExplosions[29] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_2_30);
    }

    public static Bitmap getImage(int index) {
        return cityExplosions[index];
    }

    public static int getSize() {
        return size;
    }
}
