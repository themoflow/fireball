package Physics.Play;

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

    public City(MainGamePanel g, float x, float y){

        gamePanel = g;
        setImage(BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.city));
        setx(x);
        sety(y);

    }

    public void draw(Canvas c){

        c.drawBitmap(getImage(), getx(), gety(), null);

    }
    public float getCityHeight(){
        return getImage().getHeight();
    }

    public Bitmap getImg(){
        return getImage();
    }
}
