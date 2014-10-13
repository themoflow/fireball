package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class FireballBitmaps {

    private static Bitmap[] fireballs;
    private static int size;

    public static void initialize(MainGameView g) {
        fireballs = new Bitmap[12];
        size = fireballs.length;
        fireballs[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1);
        fireballs[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1a);
        fireballs[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1b);
        fireballs[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2);
        fireballs[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2a);
        fireballs[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2b);
        fireballs[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3);
        fireballs[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3a);
        fireballs[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3b);
        fireballs[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4);
        fireballs[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4a);
        fireballs[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4b);

    }

    public static Bitmap getImage(int index) {
        return fireballs[index];
    }

    public static int getSize() {
        return size;
    }
}
