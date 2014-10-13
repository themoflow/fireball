package Physics.Play.drawableManagers;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Rocket;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class RocketManager {

    private static RocketManager r = new RocketManager();
    private boolean logEnabled = false;

    private RocketManager(){}

    public static RocketManager getInstance( ) {
        return r;
    }

    public Rocket createRocket(List<Rocket> rockets, MainGameView g, float scrWidth) {
        float height = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket).getHeight();
        Rocket rocket = new Rocket(g, 0 - height, scrWidth);
        rockets.add(rocket);
        return rocket;
    }

    public void move(List<Rocket> rockets) {
        for(int i = 0 ; i < rockets.size(); i++) {
            float y = rockets.get(i).getY();
            y += rockets.get(i).getDistanceIncrement();
            rockets.get(i).setY(y);
        }
    }

    public boolean timeToCreateRocket(List<Rocket> rockets) {
        if(!(rockets.size() > 0))
            return true;
        else if(rockets.get(rockets.size()-1).getY() - rockets.get(rockets.size()-1).getHeight() > 0)
            return true;
        else
            return false;
    }

    public List<Drawable> setAsDrawable(List<Rocket> rockets) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < rockets.size(); i++)
            converted.add(rockets.get(i));
        return converted;
    }

    public void removeRockets(List<Rocket> rockets) {
        for(int i = 0; i < rockets.size(); i++)
            if(rockets.get(i).getY() < 0)
            {
                log("ROCKET REMOVED Y = " + rockets.get(i).getY());
                rockets.remove(i);
            }
    }

    public void checkForRemoval(List<Rocket> rockets) {
        for(int i = 0; i < rockets.size(); i++)
            if(rockets.get(i).isActive() == false)
                rockets.remove(i);
    }

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: RocketManager.java :::: ", print);
    }

}
