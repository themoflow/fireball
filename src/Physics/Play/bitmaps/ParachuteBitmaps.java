package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class ParachuteBitmaps {

    private static Bitmap[] parachutes;
    private static int size;

    public static void initialize(MainGameView g) {
        parachutes = new Bitmap[10];
        size = parachutes.length;
        /*bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_1));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_2));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_3));*/
        parachutes[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_4);
        parachutes[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_5);
        parachutes[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_6);
        parachutes[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_7);
        parachutes[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_8);
        parachutes[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_9);
        parachutes[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_10);
        parachutes[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_11);
        parachutes[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_12);
        parachutes[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_13);

    }

    public static Bitmap getImage(int index) {
        return parachutes[index];
    }

    public static int getSize() {
        return size;
    }
}
