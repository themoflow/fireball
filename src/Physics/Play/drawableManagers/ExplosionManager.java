package Physics.Play.drawableManagers;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Explosion;
import Physics.Play.core.MainGamePanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/22/14.
 */
public class ExplosionManager {

    private static ExplosionManager e = new ExplosionManager();

    private ExplosionManager(){}

    public static ExplosionManager getInstance( ) {
        return e;
    }

    public List<Explosion> createExplosions(int amount, MainGamePanel g, List<Explosion> explosions) {
        for(int i = 0; i < amount; i++) {
            explosions.add(new Explosion(g));
        }
        return explosions;
    }

    public List<Explosion> createExplosion(MainGamePanel g, List<Explosion> explosions, float x, float y) {
        explosions.add(new Explosion(g, x, y));
        return explosions;
    }

    public List<Drawable> setAsDrawable(List<Explosion> explosions) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < explosions.size(); i++)
            converted.add(explosions.get(i));
        return converted;
    }

    public void setCoordinates(float x, float y, List<Explosion> explosions) {
        explosions.get(0).setX(x);
        explosions.get(0).setY(y);
    }

    public void setIsActive(boolean b, List<Explosion> explosions) {
        explosions.get(0).setIsActive(b);
    }

    public void checkForRemoval(List<Explosion> explosions) {
        for(int i = 0; i < explosions.size(); i++)
        {
            double startTime = explosions.get(i).getStartTime();
            if(System.currentTimeMillis() - startTime > 100)
                explosions.remove(i);
        }
    }
}
