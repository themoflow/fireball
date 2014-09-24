package Physics.Play.Managers;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Rocket;
import Physics.Play.main.MainGamePanel;
import android.util.Log;

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
            rockets.get(i).setY(y+4);
        }
    }

    /*public void checkForSpeed(int num, List<Rocket> rockets, MainGamePanel g) {
        switch(num)
        {
            case 60:
                for (int i = 0; i < rockets.size(); i++)
                {
                    if(rockets.get(i).getSpeed() != 2 && !(rockets.get(i).getY() > 0))
                        rockets.get(i).speedUp();
                }
                break;
            case 40:
                for (int i = 0; i < rockets.size(); i++)
                {
                    if(rockets.get(i).getSpeed() != 3 && !(rockets.get(i).getY() > 0))
                        rockets.get(i).speedUp();
                }
                break;
            case 20:
                for (int i = 0; i < rockets.size(); i++)
                {
                    if(rockets.get(i).getSpeed() != 4 && !(rockets.get(i).getY() > 0))
                        rockets.get(i).speedUp();
                }
                break;
            case 0:
                g.setLevel(2);
                break;
        }
    }*/

    public List<Drawable> setAsDrawable(List<Rocket> rockets) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < rockets.size(); i++)
            converted.add(rockets.get(i));
        return converted;
    }

}
