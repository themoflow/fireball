package Physics.Play.drawableManagers;

import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.CityExplosion;
import Physics.Play.drawables.CityExplosion;
import Physics.Play.drawables.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 10/1/14.
 */
public class CityExplosionManager {
    private static CityExplosionManager e = new CityExplosionManager();
    private boolean logEnabled = true;

    private CityExplosionManager(){}

    public static CityExplosionManager getInstance( ) {
        return e;
    }

    public List<CityExplosion> createExplosions(int amount, MainGamePanel g, List<CityExplosion> cityExplosions) {
        for(int i = 0; i < amount; i++) {
            cityExplosions.add(new CityExplosion(g));
        }
        return cityExplosions;
    }

    public void createExplosion(MainGamePanel g, List<CityExplosion> cityExplosions, float screenWidth, float screenHeight, String position) {
        if(position.equals("left"))
        {
            float y = screenHeight - (CityExplosion.getHeight() - (CityExplosion.getHeight() / 4));
            cityExplosions.add(new CityExplosion(g, -60, y));
        }
        else if(position.equals("right"))
        {
            float x = screenWidth - (CityExplosion.getWidth() - 60);
            float y = screenHeight - (CityExplosion.getHeight() - (CityExplosion.getHeight() / 4));
            cityExplosions.add(new CityExplosion(g, x, y));
        }
    }

    public List<Drawable> setAsDrawable(List<CityExplosion> cityExplosions) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < cityExplosions.size(); i++)
            converted.add(cityExplosions.get(i));
        return converted;
    }

    public void setCoordinates(float x, float y, List<CityExplosion> cityExplosions) {
        cityExplosions.get (0).setX(x);
        cityExplosions.get(0).setY(y);
    }

    public void setIsActive(boolean b, List<CityExplosion> cityExplosions) {
        cityExplosions.get(0).setIsActive(b);
    }

    public void checkForRemoval(List<CityExplosion> cityExplosions) {
        for(int i = 0; i < cityExplosions.size(); i++)
        {
            if(cityExplosions.get(i).hasExploded())
                cityExplosions.remove(i);
        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("CityExplosionManager - ", print);
    }
}
