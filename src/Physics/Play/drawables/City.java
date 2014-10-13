package Physics.Play.drawables;

import Physics.Play.bitmaps.CityBitmaps;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;

/**
 * Created with IntelliJ IDEA.
 * User: themoflow
 * Date: 6/6/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class City extends Drawable {

    public static int HITS = 0;
    private int imageIndex = 0;

    public City(MainGameView g, float x, float y){
        setWidth(CityBitmaps.getImage(0).getWidth());
        setHeight(CityBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGameView g) {

    }

    public static void addHit(){
       HITS++;
    }

    public void switchImage() {
        //setImage(CityBitmaps.getImage(1));
        imageIndex++;
        setWidth(CityBitmaps.getImage(1).getWidth());
        setHeight(CityBitmaps.getImage(1).getHeight());

    }

    @Override
    public Bitmap getImage() {
        return CityBitmaps.getImage(imageIndex);
    }

}
