package Physics.Play.drawableManagers;

import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.AtomicExplosion;
import Physics.Play.drawables.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 10/1/14.
 */
public class AtomicExplosionManager {
    private static AtomicExplosionManager e = new AtomicExplosionManager();

    private AtomicExplosionManager(){}

    public static AtomicExplosionManager getInstance( ) {
        return e;
    }

    public List<AtomicExplosion> createExplosions(int amount, MainGamePanel g, List<AtomicExplosion> cityExplosions, String explosionType) {
        for(int i = 0; i < amount; i++) {
            cityExplosions.add(new AtomicExplosion(g, explosionType));
        }
        return cityExplosions;
    }

    public List<AtomicExplosion> createExplosion(MainGamePanel g, List<AtomicExplosion> cityExplosions, float screenWidth, float screenHeight) {
        float cityExplosionCenterX = AtomicExplosion.getWidth() / 2;
        float screenCenterX = screenWidth / 2;
        float x = screenCenterX - cityExplosionCenterX;
        float y = screenHeight - (AtomicExplosion.getHeight() - 20);
        cityExplosions.add(new AtomicExplosion(g, x, y));

        return cityExplosions;
    }

    public List<Drawable> setAsDrawable(List<AtomicExplosion> cityExplosions) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < cityExplosions.size(); i++)
            converted.add(cityExplosions.get(i));
        return converted;
    }

    public void setCoordinates(float x, float y, List<AtomicExplosion> cityExplosions) {
        cityExplosions.get (0).setX(x);
        cityExplosions.get(0).setY(y);
    }

    public void setIsActive(boolean b, List<AtomicExplosion> cityExplosions) {
        cityExplosions.get(0).setIsActive(b);
    }

    public void checkForRemoval(List<AtomicExplosion> cityExplosions) {
        for(int i = 0; i < cityExplosions.size(); i++)
        {
            if(cityExplosions.get(i).hasExploded())
                cityExplosions.remove(i);
        }
    }
}
