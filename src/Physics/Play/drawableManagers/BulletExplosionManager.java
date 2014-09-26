package Physics.Play.drawableManagers;

import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.BulletExplosion;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Explosion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class BulletExplosionManager {

    private static BulletExplosionManager be = new BulletExplosionManager();

    private BulletExplosionManager(){}

    public static BulletExplosionManager getInstance( ) {
        return be;
    }

    public List<BulletExplosion> createBulletExplosions(int amount, MainGamePanel g) {
        List<BulletExplosion> bulletExplosions = new ArrayList();
        for(int i = 0; i < amount; i++) {
            bulletExplosions.add(new BulletExplosion(g));
        }
        return bulletExplosions;
    }

    public List<Drawable> setAsDrawable(List<BulletExplosion> bulletExplosions) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < bulletExplosions.size(); i++)
            converted.add(bulletExplosions.get(i));
        return converted;
    }

    public void setCoordinates(float x, float y, List<BulletExplosion> bulletExplosions) {
        bulletExplosions.get(0).setX(x);
        bulletExplosions.get(0).setY(y);
    }

    public void setIsActive(boolean b, List<BulletExplosion> explosions) {
        explosions.get(0).setIsActive(b);
    }
}
