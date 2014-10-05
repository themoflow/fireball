package Physics.Play.drawables;

import Physics.Play.core.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created with IntelliJ IDEA.
 * User: themoflow
 * Date: 6/6/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class City extends Drawable {

    public static int HITS = 0;
    private Bitmap[] bitmaps = new Bitmap[2];

    public City(MainGamePanel g, float x, float y){
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_destroyed);
        setImage(bitmaps[0]);
        setWidth(bitmaps[0].getWidth());
        setHeight(bitmaps[0].getHeight());
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {

    }

    public static void addHit(){
       HITS++;
    }

    public void switchImage() {
        setImage(bitmaps[1]);
        setWidth(bitmaps[1].getWidth());
        setHeight(bitmaps[1].getHeight());

    }

}
