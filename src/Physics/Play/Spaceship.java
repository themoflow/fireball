package Physics.Play;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Spaceship {

    private Bitmap ship;
    private float x, y, width, height;
    private Random rand = new Random();
    private int addX = 2, addY;
    private float scrW, scrH;
    private ShipRocketList shipRocketList;
    private MainGamePanel gamePanel;
    private float rocketsX;
    private int hits;


    public Spaceship(MainGamePanel g, float scrW, float scrH, ShipRocketList srl){

        ship = BitmapFactory.decodeResource(g.getResources(), R.drawable.spaceship);
        width = ship.getWidth();
        height = ship.getHeight();
        x = 0 - width;
        y = rand.nextInt((int)(scrH-(scrH/2)));
        this.scrW = scrW;
        this.scrH = scrH;
        shipRocketList = srl;
        gamePanel = g;
        setSpeed(2);
        createRocket();

    }

    public void draw(Canvas c){
        c.drawBitmap(ship,x,y,null);
    }

    public void move(){

        x += addX;

        if(x > scrW + (width/2))
        {
            addX = -addX;
            y = rand.nextInt((int)(scrH-(scrH/2)));
            createRocket();
        }
        else if(x < (0 - width) - (width/2))
        {
            addX =  Math.abs(addX);
            y = rand.nextInt((int)(scrH-(scrH/2)));
            createRocket();
        }

        //check for rocket to be drawn.
        if(rocketsX >= x-1 && rocketsX <= x+1)
        {
            addRocket(rocketsX);
            setSpeed(2);
        }
    }

    public void speedUp(){
        addX++;
    }

    public void createRocket(){
        rocketsX = rand.nextInt((int)(scrW - (Rocket.getWidth()*3)));
    }

    private void addRocket(float x){
        shipRocketList.add(x + (width/2), y + Rocket.getHeight());
    }

    public int getHits(){
        return hits;
    }

    public void setSpeed(int num){
        shipRocketList.setSpeed(num);
    }

}
