package Physics.Play;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;


public class Rocket {

    private float x,y;
    private Paint paint = new Paint();
    private static float width, height, scrWidth, scrHeight;
    private int add = 4;
    private float cityX, cityY;
    private Bitmap rocket;
    private Random rand = new Random();

    public Rocket(MainGamePanel g, float y){

        rocket = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket);
        width = rocket.getWidth();
        height = rocket.getHeight();
        scrWidth = g.getScrWidth();
        scrHeight = g.getScrHeight();
        this.x = generateRandomNumber();
        this.y = y;
        add = 1;

    }

    public Rocket(MainGamePanel g, float x, float y){

        rocket = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket);
        width = rocket.getWidth();
        height = rocket.getHeight();
        scrWidth = g.getScrWidth();
        scrHeight = g.getScrHeight();
        this.x = x;
        this.y = y;
        add = 1;
        rand = new Random();

    }

    private float generateRandomNumber(){
        return (float)rand.nextInt((int)(scrWidth-getWidth()));
    }

    public static float getWidth(){
        return width;
    }

    public static float getHeight(){
        return height;
    }

    public void move(){
        y += add;
    }

    public void draw(Canvas c){
           c.drawBitmap(rocket, x, y, null);
    }

    public int getSpeed(){
        return add;
    }

    public void speedUp(){
        add++;
    }

    public void setSpeed(int num){
        add += num;
    }

    public float getY(){
        return y;
    }

    public float getX(){
        return x;
    }

    public Bitmap getImage(){
        return rocket;
    }


}
