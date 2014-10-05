package Physics.Play.drawableManagers;

import Physics.Play.R;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Robot;
import Physics.Play.drawables.Rocket;
import Physics.Play.core.MainGamePanel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    public List<Rocket> createRockets(int amount, MainGamePanel g, float scrWidth) {
        float height = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket).getHeight();
        List<Rocket> rockets = new ArrayList();
        for(int i = 0; i < amount; i++) {
            rockets.add(new Rocket(g, (i * 2) * (- height), scrWidth));
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

    public void removeRobots(List<Rocket> rockets, List<Robot> robots) {
        for(int i = 0; i < rockets.size(); i++)
            if(rockets.get(i).getY() < 0) {
                if(rockets.get(i).hasRobot())
                    robots.remove(rockets.get(i).getRobot());
                rockets.remove(i);
            }
    }

}
