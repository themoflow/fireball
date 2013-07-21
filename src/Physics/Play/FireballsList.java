package Physics.Play;


import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class FireballsList {

    private List<Fireballs> fireballs = new ArrayList<Fireballs>();
    private int amountOfFireballs = 0;
    private MainGamePanel gamePanel;
    private float xOrig, yOrig;

    public FireballsList(MainGamePanel g, float xOrig, float yOrig){

        gamePanel = g;
        this.xOrig = xOrig;
        this.yOrig = yOrig;

    }

    public void addFireball(float x, float y){

        fireballs.add(new Fireballs(gamePanel,x,y));
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
        fireballs.get(amountOfFireballs-1).setVelocity(x, y);

    }

    public void getAndSetVelocity(float x, float y, float length){

        if(getSize() > 0)
        {
            double vertDistance = getLastYCoord() - yOrig;
            double horiDistance = getLastXCoord() - xOrig;
            double angle = Math.atan2(vertDistance,horiDistance);
            float xScale = (float)Math.cos(angle);
            float yScale =  (float)Math.sin(angle);
            float dis = (float) Math.sqrt(Math.pow(x - xOrig, 2) + Math.pow(y - yOrig, 2));
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
            fireballs.add(new Fireballs(gamePanel, xOrig, yOrig));
            amountOfFireballs++;
        }
    }

    public void checkForFireBallRemoval(){

        for(int i = 0; i < fireballs.size(); i++)
        {
            if(fireballs.get(i).getx() <= 0 - Fireballs.getWidth() || fireballs.get(i).getx() > gamePanel.getScrWidth() || fireballs.get(i).gety() < 0 - Fireballs.getHeight() || fireballs.get(i).gety() > gamePanel.getScrHeight())
            {
                fireballs.remove(i);
                amountOfFireballs--;
            }
        }

    }

    public void checkForFireballCreation(boolean down){

        if(getSize() > 0)
        {
            if(fireballs.get(amountOfFireballs-1).gety() + 48 < yOrig)
            {
                fireballs.add(new Fireballs(gamePanel, xOrig, yOrig));
                amountOfFireballs++;
            }
            else if(down == false && fireballs.get(amountOfFireballs-1).gety() <= yOrig && fireballs.get(amountOfFireballs-1).getx()+48 < xOrig)
            {
                fireballs.add(new Fireballs(gamePanel, xOrig, yOrig));
                amountOfFireballs++;
            }
            else if (down == false && fireballs.get(amountOfFireballs-1).gety() <= yOrig && fireballs.get(amountOfFireballs-1).getx() > xOrig+48)
            {
                fireballs.add(new Fireballs(gamePanel,xOrig,yOrig));
                amountOfFireballs++;
            }


        }
    }

    public boolean checkForFireballTouch(float x, float y){

        if((int)x >= getLastXCoord() - Fireballs.getWidth() && (int)x <= getLastXCoord() + (Fireballs.getWidth()*2) &&
                (int)y >= getLastYCoord() - Fireballs.getHeight() && (int)y <= getLastYCoord() + (Fireballs.getHeight()*2))
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

    public List<Fireballs> getArray(){
        return fireballs;
    }

    public void move(float x, float y){

        if(getSize() > 0)
        {
            setX(x);
            setY(y);

            if(getY() > gamePanel.getScrHeight() - Fireballs.getHeight()*2)
                setYBounds(gamePanel.getScrHeight() - Fireballs.getWidth()*2);
            if(getY() < yOrig)
                setYBounds(yOrig);
            if(getX() < (gamePanel.getScrWidth()/4))
                setXBounds(gamePanel.getScrWidth()/4);
            if(getX() > ((gamePanel.getScrWidth()/4)*3)-Fireballs.getWidth())
                setXBounds(((gamePanel.getScrWidth()/4)*3)-Fireballs.getWidth());
        }
    }
}
