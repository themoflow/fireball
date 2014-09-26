package Physics.Play.drawables;

import Physics.Play.core.MainGamePanel;
import Physics.Play.R;
import android.graphics.BitmapFactory;

/**
 * Created with IntelliJ IDEA.
 * User: themoflow
 * Date: 6/6/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class City extends Drawable {

    private static float height, width;
    private static int cityHits = 0;

    public City(MainGamePanel g, float x, float y){
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.city));
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.city).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.city).getHeight();
    }

    public static float getHeight(){
        return height;
    }

    public static float getWidth(){
        return width;
    }

    public static void addHit(){
        cityHits++;
    }

    public static int getCityHits(){
        return cityHits;
    }

    public static void setCityHits(int h){
        cityHits = h;
    }
}
