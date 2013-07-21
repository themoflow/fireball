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
public class City {


    private float x, y;
    private MainGamePanel gamePanel;
    private Bitmap city;

    public City(MainGamePanel g, float x, float y){

        gamePanel = g;
        city = BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.city);
        this.x = x;
        this.y = y;

    }

    public void draw(Canvas c){

        c.drawBitmap(city, x, y, null);

    }

    public float getHeight(){
        return city.getHeight();
    }

    public Bitmap getImg(){
        return city;
    }
}
