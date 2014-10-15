package Physics.Play.drawableManagers;

import Physics.Play.views.MainGameView;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.RocketExplosion;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/22/14.
 */
public class RocketExplosionManager {

    private static RocketExplosionManager e = new RocketExplosionManager();
    private boolean logEnabled = false;

    private RocketExplosionManager(){}

    public static RocketExplosionManager getInstance( ) {
        return e;
    }

    public List<RocketExplosion> createExplosions(int amount, MainGameView g, List<RocketExplosion> explosions) {
        for(int i = 0; i < amount; i++) {
            explosions.add(new RocketExplosion(g));
        }
        return explosions;
    }

    public List<RocketExplosion> createExplosion(MainGameView g, List<RocketExplosion> explosions, float x, float y) {
        explosions.add(new RocketExplosion(g, x, y));
        return explosions;
    }

    public List<Drawable> setAsDrawable(List<RocketExplosion> explosions) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < explosions.size(); i++)
            converted.add(explosions.get(i));
        return converted;
    }

    public void setCoordinates(float x, float y, List<RocketExplosion> explosions) {
        explosions.get(0).setX(x);
        explosions.get(0).setY(y);
    }

    public void setIsActive(boolean b, List<RocketExplosion> explosions) {
        explosions.get(0).setIsActive(b);
    }

    public void checkForRemoval(List<RocketExplosion> explosions) {
        for(int i = 0; i < explosions.size(); i++)
        {
            if(!explosions.get(i).isActive())
            {
                log("rocketExplosion removed");
                explosions.remove(i);
            }

        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("RocketExplosionManager.java - ", print + " ::::");
    }
}
