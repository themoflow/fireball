package Physics.Play.drawableManagers;

import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/28/14.
 */
public class ParachuteManager {

    private static ParachuteManager p = new ParachuteManager();

    private ParachuteManager() {
    }

    public static ParachuteManager getInstance() {
        return p;
    }

    public List<Parachute> createParachutes(int amount, MainGamePanel g) {
        List<Parachute> parachutes = new ArrayList();
        for(int i = 0; i < amount; i++)
            parachutes.add(new Parachute(g));
        return parachutes;
    }

    public List<Drawable> setAsDrawable(List<Parachute> parachutes) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < parachutes.size(); i++)
            converted.add(parachutes.get(i));
        return converted;
    }

    public void addParachutesToRobots(List<Parachute> parachutes, List<Robot> robots) {
        for(int i = 0; i < parachutes.size(); i++)
        {
            float parachuteCenterX = parachutes.get(i).getWidth() / 2;
            float robotCenterX = robots.get(0).getWidth() / 2;
            float x;
            parachutes.get(i).setX(robots.get(i).getX() - (parachuteCenterX - robotCenterX));
            parachutes.get(i).setY(robots.get(i).getY() - parachutes.get(i).getHeight());
            robots.get(i).setParachute(parachutes.get(i));
            parachutes.get(i).setRobot(robots.get(i));
        }

    }

    public void move(List<Parachute> parachutes) {

        for (int i = 0; i < parachutes.size(); i++)
        {
            parachutes.get(i).setY(parachutes.get(i).getRobot().getY() - (parachutes.get(i).getHeight() - 20));
        }
    }

    public void removeParachutes(List<Parachute> parachutes) {
        for(int i = 0; i < parachutes.size(); i++)
            if(parachutes.get(i).getY() < 0)
                parachutes.remove(i);
    }
}
