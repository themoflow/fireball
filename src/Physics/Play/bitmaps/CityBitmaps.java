package Physics.Play.bitmaps;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class CityBitmaps {

    private static Bitmap[] citys;

    public static void initialize(MainGameView g) {
        citys = new Bitmap[2];
        citys[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city);
        citys[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_destroyed);
    }

    public static Bitmap getImage(int index) {
        return citys[index];
    }

}
