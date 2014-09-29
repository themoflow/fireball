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
            float parachuteCenterX = Parachute.getWidth(0) / 2;
            float robotCenterX = Robot.getWidth() / 2;
            float x;
            if(parachuteCenterX > robotCenterX)
                x = robots.get(i).getX() - (parachuteCenterX - robotCenterX);
            else
                x = robots.get(i).getX() + robotCenterX - parachuteCenterX;

            parachutes.get(i).setX(x);
            parachutes.get(i).setY(robots.get(i).getY() - Parachute.getHeight(0));
            robots.get(i).setParachute(parachutes.get(i));
            parachutes.get(i).setRobot(robots.get(i));
        }

    }

    public void move(List<Parachute> parachutes) {

        for (int i = 0; i < parachutes.size(); i++)
        {
            parachutes.get(i).setY(parachutes.get(i).getRobot().getY() - (Parachute.getHeight(0) - 20));
        }
    }
}
