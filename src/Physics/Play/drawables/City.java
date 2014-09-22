package Physics.Play.drawables;

import Physics.Play.main.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created with IntelliJ IDEA.
 * User: themoflow
 * Date: 6/6/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class City extends Drawable {

    private MainGamePanel gamePanel;
    private static float height, width;

    public City(MainGamePanel g, float x, float y){
        gamePanel = g;
        Bitmap image = BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.city);
        setImage(image);
        width = image.getWidth();
        height = image.getHeight();
        setx(x);
        sety(y);
    }

    public void draw(Canvas c){
        c.drawBitmap(getImage(), getx(), gety(), null);
    }
    public static float getHeight(){
        return height;
    }
    public static float getWidth(){
        return width;
    }
    public Bitmap getImg(){
        return getImage();
    }
}
