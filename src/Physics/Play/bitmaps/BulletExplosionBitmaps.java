package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class BulletExplosionBitmaps {

    private static Bitmap[] bulletExplosions;

    public static void initialize(MainGameView g) {
        bulletExplosions = new Bitmap[17];
        bulletExplosions[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_1);
        bulletExplosions[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_2);
        bulletExplosions[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_3);
        bulletExplosions[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_4);
        bulletExplosions[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_5);
        bulletExplosions[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_6);
        bulletExplosions[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_7);
        bulletExplosions[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_8);
        bulletExplosions[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_9);
        bulletExplosions[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_10);
        bulletExplosions[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_11);
        bulletExplosions[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_12);
        bulletExplosions[12] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_13);
        bulletExplosions[13] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_14);
        bulletExplosions[14] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_15);
        bulletExplosions[15] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_16);
        bulletExplosions[16] = BitmapFactory.decodeResource(g.getResources(), R.drawable.bullet_explosion_17);
    }

    public static Bitmap getImage(int index) {
        //Log.i(":::: BulletExplosionBitmaps.java -> ",  "bulletExplosions index val = " + index + " ::::");
        return bulletExplosions[index];
    }

}
