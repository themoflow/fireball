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

    private static float height, width, destroyedWidth, destroyedHeight;
    private float currentWidth, currentHeight;
    public static int HITS = 0;
    private Bitmap[] bitmaps = new Bitmap[2];

    public City(MainGamePanel g, float x, float y){
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_destroyed);
        setImage(bitmaps[0]);
        currentWidth = width;
        currentHeight = height;
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.city).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.city).getHeight();
        destroyedWidth = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_destroyed).getWidth();
        destroyedHeight = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_destroyed).getHeight();
    }

    public static float getHeight(){
        return height;
    }

    public static float getWidth(){
        return width;
    }

    public float getCurrentHeight(){
        return currentHeight;
    }

    public float getCurrentWidth(){
        return currentWidth;
    }

    public static void addHit(){
       HITS++;
    }

    public void switchImage() {
        super.setImage(bitmaps[1]);

    }

}
