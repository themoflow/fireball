package Physics.Play.drawableManagers;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.RingExplosion;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 10/7/14.
 */
public class RingExplosionManager {

    private static RingExplosionManager e = new RingExplosionManager();

    private RingExplosionManager(){}

    public static RingExplosionManager getInstance( ) {
        return e;
    }

  /*  public List<RingExplosion> createExplosions(int amount, MainGamePanel g, List<RingExplosion> ringExplosions, String explosionType) {
        for(int i = 0; i < amount; i++) {
            ringExplosions.add(new RingExplosion(g));
        }
        return ringExplosions;
    }*/

    public List<RingExplosion> createExplosion(MainGameView g, List<RingExplosion> ringExplosions, float screenWidth, float screenHeight) {
        float explosionCenterX = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_1).getWidth() / 2;
        float explosionCenterY = BitmapFactory.decodeResource(g.getResources(), R.drawable.ring_explosion_1).getHeight() / 2;
        float screenCenterX = screenWidth / 2;
        float screenCenterY = screenHeight / 2;
        float x = screenCenterX - explosionCenterX;
        float y = screenCenterY - explosionCenterY;
        ringExplosions.add(new RingExplosion(g, x, y));

        return ringExplosions;
    }

    public List<Drawable> setAsDrawable(List<RingExplosion> cityExplosions) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < cityExplosions.size(); i++)
            converted.add(cityExplosions.get(i));
        return converted;
    }

    public void setCoordinates(float x, float y, List<RingExplosion> cityExplosions) {
        cityExplosions.get (0).setX(x);
        cityExplosions.get(0).setY(y);
    }

    public void setIsActive(boolean b, List<RingExplosion> cityExplosions) {
        cityExplosions.get(0).setIsActive(b);
    }

    public void checkForRemoval(List<RingExplosion> ringExplosions) {
        for(int i = 0; i < ringExplosions.size(); i++)
        {
            if(!ringExplosions.get(i).isActive())
                ringExplosions.remove(i);
        }
    }
}
