package Physics.Play;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Explosion {

    private static Bitmap explosion;
    private float x, y;


    public Explosion(MainGamePanel g){

        explosion =  BitmapFactory.decodeResource(g.getResources(), R.drawable.explosion);

    }

    public static Bitmap getImg(){
        return explosion;
    }

    public void draw(Canvas c){
        c.drawBitmap(explosion, x, y,null);
    }

    public void setCoordinates(float x, float y){

        this.x = x;
        this.y = y;

    }
}
