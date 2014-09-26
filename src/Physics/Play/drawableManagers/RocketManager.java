package Physics.Play.drawableManagers;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Rocket;
import Physics.Play.core.MainGamePanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class RocketManager {

    private static RocketManager r = new RocketManager();

    private RocketManager(){}

    public static RocketManager getInstance( ) {
        return r;
    }

    public List<Rocket> createRockets(int amount, MainGamePanel g) {
        List<Rocket> rockets = new ArrayList();
        for(int i = 0; i < amount; i++) {
            rockets.add(new Rocket(g, (i * 2) * (-Rocket.getHeight())));
        }
        return rockets;
    }

    public void move(List<Rocket> rockets) {
        for(int i = 0 ; i < rockets.size(); i++) {
            float y = rockets.get(i).getY();
            y += rockets.get(i).getDistanceIncrement();
            rockets.get(i).setY(y);
        }
    }

    public List<Drawable> setAsDrawable(List<Rocket> rockets) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < rockets.size(); i++)
            converted.add(rockets.get(i));
        return converted;
    }

}
