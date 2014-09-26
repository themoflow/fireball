package Physics.Play.drawableManagers;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Robot;
import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.Rocket;
import java.util.*;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class RobotManager {

    private MainGamePanel gamePanel;

    public RobotManager(MainGamePanel g) {
        gamePanel = g;
    }

    public List<Robot> createRobots(int amount) {
        List<Robot> robots = new ArrayList();
        for(int i = 0; i < amount; i++)
            robots.add(new Robot(gamePanel));
        return robots;
    }

    public void addRobotsToRockets(List<Rocket> rockets, List<Robot> robots) {
        for(int i = 0; i < robots.size(); i++)
        {
            float rocketWidthCenter = Rocket.getWidth() / 2;
            float robotWidthCenter = Robot.getWidth() / 2;
            float robotOffset = robotWidthCenter - rocketWidthCenter;
            float x = rockets.get(i).getX() - robotOffset;

            robots.get(i).setX(x);
            robots.get(i).setY(rockets.get(i).getY());
            rockets.get(i).setRobot(robots.get(i));
            robots.get(i).setRocket(rockets.get(i));

        }
    }

    public void move(List<Robot> robots) {
        for(int i = 0; i < robots.size(); i++)
            robots.get(i).setY(robots.get(i).getRocket().getY()+15);
    }

    public List<Drawable> setAsDrawable(List<Robot> robots) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < robots.size(); i++)
            converted.add(robots.get(i));
        return converted;
    }
}
