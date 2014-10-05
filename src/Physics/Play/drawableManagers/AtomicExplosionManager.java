package Physics.Play.drawableManagers;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.AtomicExplosion;
import Physics.Play.drawables.Drawable;
import android.graphics.BitmapFactory;

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

    public List<AtomicExplosion> createExplosions(int amount, MainGamePanel g, List<AtomicExplosion> atomicExplosions, String explosionType) {
        for(int i = 0; i < amount; i++) {
            atomicExplosions.add(new AtomicExplosion(g));
        }
        return atomicExplosions;
    }

    public List<AtomicExplosion> createExplosion(MainGamePanel g, List<AtomicExplosion> atomicExplosions, float screenWidth, float screenHeight) {
        float cityExplosionCenterX = BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_1).getWidth() / 2;
        float screenCenterX = screenWidth / 2;
        float x = screenCenterX - cityExplosionCenterX;
        float y = screenHeight - (BitmapFactory.decodeResource(g.getResources(), R.drawable.city_explosion_1).getHeight() - 20);
        atomicExplosions.add(new AtomicExplosion(g, x, y));

        return atomicExplosions;
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
