package Physics.Play.drawableManagers;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Shield;
import android.graphics.BitmapFactory;

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

    public void createShields(MainGameView g, List<Shield> shields, float screenWidth, float screenHeight) {
        float x = (screenWidth / 2) - (BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_perimeter).getWidth() / 2);
        float y = screenHeight - (BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_perimeter).getHeight() - 40);
        Shield shield = new Shield(g, x, y);
        shields.add(shield);
    }

    public void createShield(List<Shield> shields, MainGameView g, float screenWidth, float screenHeight) {
        float x = (screenWidth / 2) - (BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_perimeter).getWidth() / 2);
        float y = screenHeight - (BitmapFactory.decodeResource(g.getResources(), R.drawable.shield_perimeter).getHeight() - 40);
        Shield shield = new Shield(g, x, y);
        shields.add(shield);
    }

    public List<Drawable> setAsDrawable(List<Shield> shields) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < shields.size(); i++)
            converted.add(shields.get(i));
        return converted;
    }

    public void addHit(List<Shield> shields) {
        for(int i = 0; i < shields.size(); i++)
            shields.get(i).addHit();
    }

    public void setHits(List<Shield> shields, int amount) {
        for(int i = 0; i < shields.size(); i++)
            shields.get(i).setHits(amount);
    }

    public void checkForRemoval(List<Shield> shields) {
        for(int i = 0; i < shields.size(); i++)
            if(!shields.get(i).isActive())
                shields.remove(i);
    }
}
