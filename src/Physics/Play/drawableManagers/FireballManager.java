package Physics.Play.drawableManagers;

import Physics.Play.views.MainGameView;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/22/14.
 */
public class FireballManager {

    private static FireballManager f = new FireballManager();
    private boolean logEnabled = false;

    private FireballManager(){}

    public static FireballManager getInstance( ) {
        return f;
    }

    public void createFireballs(Activity a, int amount, List<Fireball> fireballs, MainGameView g, float scrWidth, float scrHeight) {
        for(int i = 0; i < amount; i++) {
            fireballs.add(new Fireball(g, scrWidth, scrHeight, a));
        }
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

    public void addFireball(float x, float y, List<Fireball> fireballs, MainGameView g, Activity a){
        fireballs.add(new Fireball(g,x,y,a));
    }

    public float getLastXCoord(List<Fireball> fireballs){
        if(fireballs.size() > 0)
            return fireballs.get(fireballs.size()-1).getX();
        else
            return 0;
    }

    public float getLastYCoord(List<Fireball> fireballs){
        if(fireballs.size() > 0)
            return fireballs.get(fireballs.size()-1).getY();
        else
            return 0;
    }

    public void setVelocity(float x, float y, List<Fireball> fireballs){
        fireballs.get(fireballs.size()-1).setVelocityX(x);
        fireballs.get(fireballs.size()-1).setVelocityY(y);

    }

    public void getAndSetVelocity(float x, float y, float length, List<Fireball> fireballs){
        if(fireballs.size() > 0)
        {
            float lastX = getLastXCoord(fireballs);
            float lastY = getLastYCoord(fireballs);
            double horiDistance = lastX - fireballs.get(0).getOriginX();
            double vertDistance = lastY - fireballs.get(0).getOriginY();
            double angle = Math.atan2(vertDistance,horiDistance);
            float xScale = (float)Math.cos(angle);
            float yScale =  (float)Math.sin(angle);
            float dis = (float) Math.sqrt(Math.pow(lastX - fireballs.get(0).getOriginX(), 2) + Math.pow(lastY - fireballs.get(0).getOriginY(), 2));
            float speed = (dis / length);
            float xVelocity = (speed*xScale);
            float yVelocity = (speed*yScale);
            setVelocity(xVelocity, yVelocity, fireballs);
        }
    }

    public void isAtleastOneFireball(List<Fireball> fireballs, MainGameView g, float scrWidth, float scrHeight, Activity a){
        if(fireballs.size() <= 0)
        {
            fireballs.add(new Fireball(g, scrWidth, scrHeight, a));
        }
    }

    public void checkForFireBallRemoval(List<Fireball> fireballs, MainGameView g){
        synchronized(fireballs)
        {
            for (int i = 0; i < fireballs.size(); i++) {
                if (fireballs.get(i).getX() <= 0 - fireballs.get(i).getWidth() || fireballs.get(i).getX() > g.getScreenWidth() || fireballs.get(i).getY() < 0 - fireballs.get(i).getHeight() || fireballs.get(i).getY() > g.getScreenHeight()) {
                    fireballs.get(i).cancelTimer();
                    fireballs.remove(i);
                }
            }
        }

    }

    public void checkForFireballCreation(boolean down, List<Fireball> fireballs, MainGameView g, float scrWidth, float scrHeight, Activity a){

        if(fireballs.size() > 0)
        {
            if(fireballs.get(fireballs.size()-1).getY() + 48 < fireballs.get(0).getOriginY())
            {
                fireballs.add(new Fireball(g, scrWidth, scrHeight, a));
            }
            else if(down == false && fireballs.get(fireballs.size()-1).getY() <= fireballs.get(0).getOriginY() && fireballs.get(fireballs.size()-1).getX()+48 < fireballs.get(0).getOriginX())
            {
                fireballs.add(new Fireball(g, scrWidth, scrHeight,a ));
            }
            else if (down == false && fireballs.get(fireballs.size()-1).getY() <= fireballs.get(0).getOriginY() && fireballs.get(fireballs.size()-1).getX() > fireballs.get(0).getOriginX()+48)
            {
                fireballs.add(new Fireball(g, scrWidth, scrHeight, a));
            }


        }
    }

    public boolean checkForFireballTouch(float x, float y, List<Fireball> fireballs){
        log("fireball size = " + fireballs.size());
        if(getLastXCoord(fireballs) == 0 || getLastYCoord(fireballs) == 0)
        {
            return false;
        }
        else if((int)x >= getLastXCoord(fireballs) - fireballs.get(0).getWidth() && (int)x <= getLastXCoord(fireballs) + (fireballs.get(0).getWidth()*2) &&
                (int)y >= getLastYCoord(fireballs) - fireballs.get(0).getHeight() && (int)y <= getLastYCoord(fireballs) + (fireballs.get(0).getHeight()*2))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void onDrag(float x, float y, List<Fireball> fireballs, MainGameView g){
        synchronized(fireballs)
        {
            if (fireballs.size() > 0) {
                if (fireballs.get(fireballs.size() - 1) != null) {
                    fireballs.get(fireballs.size() - 1).setX(centerX(x, fireballs));
                    fireballs.get(fireballs.size() - 1).setY(centerY(y, fireballs));
                    //Set the Y coordinate boundry area for the bottom of screen.
                    if (fireballs.get(fireballs.size() - 1).getY() > g.getScreenHeight() - fireballs.get(0).getHeight())
                        fireballs.get(fireballs.size() - 1).setY(g.getScreenHeight() - fireballs.get(0).getHeight());
                    //Set the y coordinate boundry area for the top.
                    if (fireballs.get(fireballs.size() - 1).getY() < fireballs.get(0).getOriginY())
                        fireballs.get(fireballs.size() - 1).setY(fireballs.get(0).getOriginY());
                    //Set the x coordinate boundry area for the left of the screen.
                    if (fireballs.get(fireballs.size() - 1).getX() < 0)
                        fireballs.get(fireballs.size() - 1).setX(0);
                    //Set the x coordinate boundry area for the right of the screen.
                    if (fireballs.get(fireballs.size() - 1).getX() > g.getScreenWidth() - fireballs.get(0).getWidth())
                        fireballs.get(fireballs.size() - 1).setX(g.getScreenWidth() - fireballs.get(0).getWidth());
                }
            }
        }
    }

    public Fireball getLastFireball(List<Fireball> fireballs) {
        synchronized(fireballs) {
            return fireballs.get(fireballs.size()-1);
        }
    }

    public int getSize(List<Fireball> fireballs) {
        synchronized(fireballs) {
            return fireballs.size();
        }
    }

    private float centerX(float x, List<Fireball> fireballs){
        return x - (fireballs.get(0).getWidth() / 2);
    }

    private float centerY(float y, List<Fireball> fireballs){
        return y - (fireballs.get(0).getWidth() / 2);
    }

    public void checkForRemoval(List<Fireball> fireballs) {
        synchronized(fireballs)
        {
            for (int i = 0; i < fireballs.size(); i++)
                if (fireballs.get(i).isActive() == false)
                    fireballs.remove(i);
        }
    }

    public Fireball getFirstFireball(List<Fireball> fireballs) {
        synchronized(fireballs) {
            return fireballs.get(0);
        }
    }

    public void removeFireballs(List<Fireball> fireballs) {
        synchronized(fireballs)
        {
            for (int i = 0; i < fireballs.size(); i++)
                fireballs.remove(i);

        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: FireballManager.java :::: ", print);
    }

}
