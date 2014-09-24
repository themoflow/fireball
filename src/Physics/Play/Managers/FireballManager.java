package Physics.Play.Managers;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import Physics.Play.main.MainGamePanel;
import java.util.ArrayList;
import java.util.List;

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
            float x = fireballs.get(i).getX();
            float y = fireballs.get(i).getY();
            x -= fireballs.get(i).getVelocityX();
            y -= fireballs.get(i).getVelocityY();
            fireballs.get(i).setX(x);
            fireballs.get(i).setY(y);
        }
    }

    public void addFireball(float x, float y, List<Fireball> fireballs, MainGamePanel g){
        fireballs.add(new Fireball(g,x,y));
    }

    public float getLastXCoord(List<Fireball> fireballs){
        return fireballs.get(fireballs.size()-1).getX();
    }

    public float getLastYCoord(List<Fireball> fireballs){
        return fireballs.get(fireballs.size()-1).getY();
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
            if(fireballs.get(i).getX() <= 0 - Fireball.getWidth() || fireballs.get(i).getX() > g.getScrWidth() || fireballs.get(i).getY() < 0 - Fireball.getHeight() || fireballs.get(i).getY() > g.getScrHeight())
            {
                fireballs.remove(i);
            }
        }

    }

    public void checkForFireballCreation(boolean down, List<Fireball> fireballs, MainGamePanel g){

        if(fireballs.size() > 0)
        {
            if(fireballs.get(fireballs.size()-1).getY() + 48 < Fireball.getOriginY())
            {
                fireballs.add(new Fireball(g, Fireball.getOriginX(), Fireball.getOriginY()));
            }
            else if(down == false && fireballs.get(fireballs.size()-1).getY() <= Fireball.getOriginY() && fireballs.get(fireballs.size()-1).getX()+48 < Fireball.getOriginX())
            {
                fireballs.add(new Fireball(g, Fireball.getOriginX(), Fireball.getOriginY()));
            }
            else if (down == false && fireballs.get(fireballs.size()-1).getY() <= Fireball.getOriginY() && fireballs.get(fireballs.size()-1).getX() > Fireball.getOriginX()+48)
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
            fireballs.get(fireballs.size()-1).setX(x);
            fireballs.get(fireballs.size()-1).setY(y);

            if(fireballs.get(fireballs.size()-1).getY() > g.getScrHeight() - Fireball.getHeight() * 2)
                fireballs.get(fireballs.size()-1).setY(g.getScrHeight() - Fireball.getHeight() * 2);
            if(fireballs.get(fireballs.size()-1).getY() < Fireball.getOriginY())
                fireballs.get(fireballs.size()-1).setY(Fireball.getOriginY());
            if(fireballs.get(fireballs.size()-1).getX() < (g.getScrWidth() / 4)) {
                fireballs.get(fireballs.size()-1).setX(g.getScrWidth() / 4);
            }
            if(fireballs.get(fireballs.size()-1).getX() > ((g.getScrWidth() / 4) * 3) - Fireball.getWidth()) {
                fireballs.get(fireballs.size()-1).setX(((g.getScrWidth() / 4) * 3) - Fireball.getWidth());
            }
        }
    }
}
