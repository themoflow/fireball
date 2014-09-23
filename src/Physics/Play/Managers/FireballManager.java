package Physics.Play.Managers;

import Physics.Play.drawables.City;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import Physics.Play.main.MainGamePanel;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 9/22/14.
 */
public class FireballManager {

    private static FireballManager f = new FireballManager();

    private FireballManager(){}

    public static FireballManager getInstance( ) {
        return f;
    }

    public List<Fireball> createFireballs(int amount, MainGamePanel g, float origX, float origY) {
        List<Fireball> fireballs = new ArrayList();
        for(int i = 0; i < amount; i++) {
            fireballs.add(new Fireball(g, origX, origY));
        }
        return fireballs;
    }

    public List<Drawable> setAsDrawable(List<Fireball> fireballs) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < fireballs.size(); i++)
            converted.add(fireballs.get(i));
        return converted;
    }

    public void addVelocity(List<Fireball> fireballs){
        for(int i = 0; i < fireballs.size(); i++)
        {
            float x = fireballs.get(i).getx();
            float y = fireballs.get(i).gety();
            x -= fireballs.get(i).getVelocityX();
            y -= fireballs.get(i).getVelocityY();
            fireballs.get(i).setx(x);
            fireballs.get(i).sety(y);
        }
    }

    public void addFireball(float x, float y, List<Fireball> fireballs, MainGamePanel g){
        fireballs.add(new Fireball(g,x,y));
    }

    public float getLastXCoord(List<Fireball> fireballs){
        return fireballs.get(fireballs.size()-1).getx();
    }

    public float getLastYCoord(List<Fireball> fireballs){
        return fireballs.get(fireballs.size()-1).gety();
    }

    public void setVelocity(float x, float y, List<Fireball> fireballs){
        fireballs.get(fireballs.size()-1).setVelocityX(x);
        fireballs.get(fireballs.size()-1).setVelocityY(y);

    }

    public void getAndSetVelocity(float x, float y, float length, List<Fireball> fireballs){

        if(fireballs.size() > 0)
        {
            double vertDistance = getLastYCoord(fireballs) - Fireball.getOriginY();
            double horiDistance = getLastXCoord(fireballs) - Fireball.getOriginX();
            double angle = Math.atan2(vertDistance,horiDistance);
            float xScale = (float)Math.cos(angle);
            float yScale =  (float)Math.sin(angle);
            float dis = (float) Math.sqrt(Math.pow(x - Fireball.getOriginX(), 2) + Math.pow(y - Fireball.getOriginY(), 2));
            float speed = (dis / length);
            float xVelocity = (speed*xScale);
            float yVelocity = (speed*yScale);
            setVelocity(xVelocity, yVelocity, fireballs);
        }
    }

    public void isAtleastOneFireball(List<Fireball> fireballs, MainGamePanel g){

        if(fireballs.size() <= 0)
        {
            fireballs.add(new Fireball(g, Fireball.getOriginX(), Fireball.getOriginY()));
        }
    }

    public void checkForFireBallRemoval(List<Fireball> fireballs, MainGamePanel g){

        for(int i = 0; i < fireballs.size(); i++)
        {
            if(fireballs.get(i).getx() <= 0 - Fireball.getWidth() || fireballs.get(i).getx() > g.getScrWidth() || fireballs.get(i).gety() < 0 - Fireball.getHeight() || fireballs.get(i).gety() > g.getScrHeight())
            {
                fireballs.remove(i);
            }
        }

    }

    public void checkForFireballCreation(boolean down, List<Fireball> fireballs, MainGamePanel g){

        if(fireballs.size() > 0)
        {
            if(fireballs.get(fireballs.size()-1).gety() + 48 < Fireball.getOriginY())
            {
                fireballs.add(new Fireball(g, Fireball.getOriginX(), Fireball.getOriginY()));
            }
            else if(down == false && fireballs.get(fireballs.size()-1).gety() <= Fireball.getOriginY() && fireballs.get(fireballs.size()-1).getx()+48 < Fireball.getOriginX())
            {
                fireballs.add(new Fireball(g, Fireball.getOriginX(), Fireball.getOriginY()));
            }
            else if (down == false && fireballs.get(fireballs.size()-1).gety() <= Fireball.getOriginY() && fireballs.get(fireballs.size()-1).getx() > Fireball.getOriginX()+48)
            {
                fireballs.add(new Fireball(g,Fireball.getOriginX(),Fireball.getOriginY()));
            }


        }
    }

    public boolean checkForFireballTouch(float x, float y, List<Fireball> fireballs){

        if((int)x >= getLastXCoord(fireballs) - Fireball.getWidth() && (int)x <= getLastXCoord(fireballs) + (Fireball.getWidth()*2) &&
                (int)y >= getLastYCoord(fireballs) - Fireball.getHeight() && (int)y <= getLastYCoord(fireballs) + (Fireball.getHeight()*2))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void onDrag(float x, float y, List<Fireball> fireballs, MainGamePanel g){
        if(fireballs.size() > 0)
        {
            fireballs.get(fireballs.size()-1).setx(x);
            fireballs.get(fireballs.size()-1).sety(y);

            if(fireballs.get(fireballs.size()-1).gety() > g.getScrHeight() - Fireball.getHeight() * 2)
                fireballs.get(fireballs.size()-1).sety(g.getScrHeight() - Fireball.getHeight() * 2);
            if(fireballs.get(fireballs.size()-1).gety() < Fireball.getOriginY())
                fireballs.get(fireballs.size()-1).sety(Fireball.getOriginY());
            if(fireballs.get(fireballs.size()-1).getx() < (g.getScrWidth() / 4)) {
                fireballs.get(fireballs.size()-1).setx(g.getScrWidth() / 4);
            }
            if(fireballs.get(fireballs.size()-1).getx() > ((g.getScrWidth() / 4) * 3) - Fireball.getWidth()) {
                fireballs.get(fireballs.size()-1).setx(((g.getScrWidth() / 4) * 3) - Fireball.getWidth());
            }
        }
    }
}
