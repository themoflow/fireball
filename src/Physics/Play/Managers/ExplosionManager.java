package Physics.Play.Managers;

import Physics.Play.drawables.City;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Explosion;
import Physics.Play.main.MainGamePanel;

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

    public List<Explosion> createExplosions(int amount, MainGamePanel g) {
        List<Explosion> explosions = new ArrayList();
        for(int i = 0; i < amount; i++) {
            explosions.add(new Explosion(g));
        }
        return explosions;
    }

    public List<Drawable> setAsDrawable(List<Explosion> explosions) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < explosions.size(); i++)
            converted.add(explosions.get(i));
        return converted;
    }
}