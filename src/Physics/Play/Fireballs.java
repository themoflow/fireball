package Physics.Play;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Fireballs {

    private Bitmap[] imgFireball = new Bitmap[12];
    private float x,y;
    private float xVeloc, yVeloc;
    private static float width, height;
    private TimerTask timerTask;
    private long delay = 50L;
    private long replay = 50L;
    private int drawImage;


    public Fireballs(MainGamePanel g, float x, float y){

        imgFireball[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1);
        imgFireball[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1a);
        imgFireball[2] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball1b);
        imgFireball[3] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2);
        imgFireball[4] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2a);
        imgFireball[5] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball2b);
        imgFireball[6] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3);
        imgFireball[7] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3a);
        imgFireball[8] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball3b);
        imgFireball[9] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4);
        imgFireball[10] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4a);
        imgFireball[11] = BitmapFactory.decodeResource(g.getResources(), R.drawable.fireball4b);

        this.x = x;
        this.y = y;
        width = imgFireball[0].getWidth();
        height = imgFireball[0].getHeight();
        setTimerTask();

    }

    public void draw(Canvas c){
        c.drawBitmap(getImage(), x, y, null);
    }

    public Bitmap getImage(){
        return imgFireball[drawImage];

    }

    public float getx(){
        return x;
    }

    public float gety(){
        return y;
    }

    public void setx(float x){
        this.x = x;
    }

    public void sety(float y){
        this.y = y;
    }

    public void addVelocity(){
        x -= xVeloc;
        y -= yVeloc;
    }

    public void setVelocity(float x, float y){
        xVeloc = x;
        yVeloc = y;

    }

    private void setTimerTask(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                setImage();
            }
        } ;
        new Timer().scheduleAtFixedRate(timerTask, delay, replay);
    }

    private void setImage(){

        switch(drawImage)
        {
            case 0:
                drawImage = 1;
                break;
            case 1:
                drawImage = 2;
                break;
            case 2:
                drawImage = 3;
                break;
            case 3:
                drawImage = 4;
                break;
            case 4:
                drawImage = 5;
                break;
            case 5:
                drawImage = 6;
                break;
            case 6:
                drawImage = 7;
                break;
            case 7:
                drawImage = 8;
                break;
            case 8:
                drawImage = 9;
                break;
            case 9:
                drawImage = 10;
                break;
            case 10:
                drawImage = 11;
                break;
            case 11:
                drawImage = 0;
                break;
        }

    }

    public static float getWidth(){
        return width;
    }

    public static float getHeight(){
        return height;
    }




}
