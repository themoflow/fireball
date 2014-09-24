package Physics.Play.Managers;

import Physics.Play.drawables.Robot;
import Physics.Play.main.MainGamePanel;
import java.util.*;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class RobotManager {

    private Random rand = new Random();
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


}
