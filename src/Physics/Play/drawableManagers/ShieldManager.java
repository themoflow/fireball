package Physics.Play.drawableManagers;

import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.AtomicExplosion;
import Physics.Play.drawables.City;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Shield;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 10/3/14.
 */
public class ShieldManager {

    private static ShieldManager s = new ShieldManager();

    private ShieldManager(){}

    public static ShieldManager getInstance( ) {
        return s;
    }

    public List<Shield> createShields(MainGamePanel g, float screenWidth, float screenHeight) {
        List<Shield> shields = new ArrayList();
        float x = (screenWidth / 2) - (Shield.getWidth() / 2);
        float y = screenHeight - (Shield.getHeight() - 40);
        Shield shield = new Shield(g, x, y);
        shields.add(shield);
        return shields;
    }

    public void createShield(List<Shield> shields, MainGamePanel g, float screenWidth, float screenHeight) {
        float x = (screenWidth / 2) - (Shield.getWidth() / 2);
        float y = screenHeight - (Shield.getHeight() - 40);
        Shield shield = new Shield(g, x, y);
        shields.add(shield);
    }

    public List<Drawable> setAsDrawable(List<Shield> shields) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < shields.size(); i++)
            converted.add(shields.get(i));
        return converted;
    }

    public void checkForRemoval(List<Shield> shields) {
        for(int i = 0; i < shields.size(); i++)
        {
            if(!shields.get(i).isActive() || Shield.getHits() >= 15)
                shields.remove(i);
        }
    }
}
