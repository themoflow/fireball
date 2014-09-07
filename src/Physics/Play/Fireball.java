package Physics.Play;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Fireball extends Drawable {

    private Bitmap[] imgFireball = new Bitmap[12];
    private float xVeloc, yVeloc;
    private TimerTask timerTask;
    private long delay = 50L;
    private long replay = 50L;
    private int imageIndex;


    public Fireball(MainGamePanel g, float x, float y){

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

        setx(x);
        sety(y);
        super.setImage(imgFireball[0]);
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
        setTimerTask();

    }

    public void draw(Canvas c){
        c.drawBitmap(getImage(), getx(), gety(), null);
    }

    public Bitmap getImage(){
        return super.getImage();

    }

    public void addVelocity(){
        float x = getx();
        float y = gety();
        x -= xVeloc;
        y -= yVeloc;
        setx(x);
        sety(y);
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

        if(imageIndex == 11)
            imageIndex = 0;
        else
            imageIndex++;

        super.setImage(imgFireball[imageIndex]);

        /*switch(drawImage)
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
        }*/

    }






}
