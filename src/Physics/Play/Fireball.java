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
    private List<Fireball> fireballs = new ArrayList<Fireball>();
    public int amountOfFireballs = 0;
    private MainGamePanel gamePanel;
    private static float xOrigin, yOrigin;


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
        this.gamePanel = g;

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

    public void setVelocity2(float x, float y){
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

    }

    public static void initialize(MainGamePanel g, float xOrig, float yOrig) {
        xOrigin = xOrig;
        yOrigin = yOrig;
    }

    public void addFireball(float x, float y){
        fireballs.add(new Fireball(gamePanel,x,y));
        amountOfFireballs++;
    }

    public void drawAll(Canvas c){
        for (int i = 0; i < fireballs.size(); i++)
        {
            fireballs.get(i).addVelocity();
            fireballs.get(i).draw(c);
        }
    }

    public float getLastXCoord(){
        return fireballs.get(amountOfFireballs-1).getx();
    }

    public float getLastYCoord(){
        return fireballs.get(amountOfFireballs-1).gety();
    }

    public int getSize(){
        return fireballs.size();
    }

    public void setVelocity(float x, float y){
        fireballs.get(amountOfFireballs-1).setVelocity2(x, y);

    }

    public void getAndSetVelocity(float x, float y, float length){

        if(getSize() > 0)
        {
            double vertDistance = getLastYCoord() - yOrigin;
            double horiDistance = getLastXCoord() - xOrigin;
            double angle = Math.atan2(vertDistance,horiDistance);
            float xScale = (float)Math.cos(angle);
            float yScale =  (float)Math.sin(angle);
            float dis = (float) Math.sqrt(Math.pow(x - xOrigin, 2) + Math.pow(y - yOrigin, 2));
            float speed = (dis / length);
            float xVelocity = (speed*xScale);
            float yVelocity = (speed*yScale);
            setVelocity(xVelocity, yVelocity);
        }
    }

    public void setX(float x){
        fireballs.get(amountOfFireballs-1).setx(x - fireballs.get(amountOfFireballs-1).getWidth()/2);
    }

    public void setY(float y){
        fireballs.get(amountOfFireballs-1).sety(y - fireballs.get(amountOfFireballs - 1).getHeight() / 2);
    }

    public float getY(){
        return  fireballs.get(amountOfFireballs-1).gety();
    }

    public float getX(){
        return  fireballs.get(amountOfFireballs-1).getx();
    }

    public void setYBounds(float y){
        fireballs.get(amountOfFireballs-1).sety(y);
    }

    public void setXBounds(float x){
        fireballs.get(amountOfFireballs-1).setx(x);
    }
    public void checkFor0Fireballs(){

        if(fireballs.size() <= 0)
        {
            fireballs.add(new Fireball(gamePanel, xOrigin, yOrigin));
            amountOfFireballs++;
        }
    }

    public void checkForFireBallRemoval(){

        for(int i = 0; i < fireballs.size(); i++)
        {
            if(fireballs.get(i).getx() <= 0 - Fireball.getWidth() || fireballs.get(i).getx() > gamePanel.getScrWidth() || fireballs.get(i).gety() < 0 - Fireball.getHeight() || fireballs.get(i).gety() > gamePanel.getScrHeight())
            {
                fireballs.remove(i);
                amountOfFireballs--;
            }
        }

    }

    public void checkForFireballCreation(boolean down){

        if(getSize() > 0)
        {
            if(fireballs.get(amountOfFireballs-1).gety() + 48 < yOrigin)
            {
                fireballs.add(new Fireball(gamePanel, xOrigin, yOrigin));
                amountOfFireballs++;
            }
            else if(down == false && fireballs.get(amountOfFireballs-1).gety() <= yOrigin && fireballs.get(amountOfFireballs-1).getx()+48 < xOrigin)
            {
                fireballs.add(new Fireball(gamePanel, xOrigin, yOrigin));
                amountOfFireballs++;
            }
            else if (down == false && fireballs.get(amountOfFireballs-1).gety() <= yOrigin && fireballs.get(amountOfFireballs-1).getx() > xOrigin+48)
            {
                fireballs.add(new Fireball(gamePanel,xOrigin,yOrigin));
                amountOfFireballs++;
            }


        }
    }

    public boolean checkForFireballTouch(float x, float y){

        if((int)x >= getLastXCoord() - Fireball.getWidth() && (int)x <= getLastXCoord() + (Fireball.getWidth()*2) &&
                (int)y >= getLastYCoord() - Fireball.getHeight() && (int)y <= getLastYCoord() + (Fireball.getHeight()*2))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void updateFireballAmount(){
        amountOfFireballs--;
    }

    public List<Fireball> getArray(){
        return fireballs;
    }

    public void move(float x, float y){

        if(getSize() > 0)
        {
            setX(x);
            setY(y);

            if(getY() > gamePanel.getScrHeight() - Fireball.getHeight()*2)
                setYBounds(gamePanel.getScrHeight() - Fireball.getWidth()*2);
            if(getY() < yOrigin)
                setYBounds(yOrigin);
            if(getX() < (gamePanel.getScrWidth()/4))
                setXBounds(gamePanel.getScrWidth()/4);
            if(getX() > ((gamePanel.getScrWidth()/4)*3)-Fireball.getWidth())
                setXBounds(((gamePanel.getScrWidth()/4)*3)-Fireball.getWidth());
        }
    }

    public void remove(int i){
        fireballs.remove(i);
    }
}
