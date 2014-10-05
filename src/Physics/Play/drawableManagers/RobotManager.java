package Physics.Play.drawableManagers;

import Physics.Play.drawables.*;
import Physics.Play.core.MainGamePanel;
import Physics.Play.helpers.CollisionDetector;
import Physics.Play.helpers.Coordinate;
import android.util.Log;

import java.util.*;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class RobotManager {

    private double seconds = 10;
    private static RobotManager r = new RobotManager();

    private RobotManager() {

    }

    public static RobotManager getInstance() {
        return r;
    }

    public List<Robot> createRobots(int amount, MainGamePanel g) {
        List<Robot> robots = new ArrayList();
        for(int i = 0; i < amount; i++)
            robots.add(new Robot(g));
        return robots;
    }

    public void addRobotsToRockets(List<Rocket> rockets, List<Robot> robots) {
        for(int i = 0; i < robots.size(); i++)
        {
            float rocketWidthCenter = rockets.get(0).getWidth() / 2;
            float robotWidthCenter = robots.get(0).getWidth() / 2;
            float robotOffset = robotWidthCenter - rocketWidthCenter;
            float x = rockets.get(i).getX() - robotOffset;

            robots.get(i).setX(x);
            robots.get(i).setY(rockets.get(i).getY());
            rockets.get(i).setRobot(robots.get(i));
            robots.get(i).setRocket(rockets.get(i));
        }
    }

    public void move(List<Robot> robots, CollisionDetector collisionDetector, MainGamePanel g) {

        for(int i = 0; i < robots.size(); i++)
        {
            if(robots.get(i).isJumping())
            {
                moveRobotOnACurve(robots.get(i));
            }
            else if(robots.get(i).isOnRocket())
            {
                if(robots.get(i).isReadyToJump())
                {
                    setJumpCoordinates(robots.get(i));
                    if(!collisionDetector.willRobotCollideWithAnotherRobot(robots, g))
                    {
                        robots.get(i).setIsJumping(true);
                        robots.get(i).setRobotJumpingImage();
                        robots.get(i).setIsOnRocket(false);
                        robots.get(i).getRocket().setHasRobot(false);
                        robots.get(i).getParachute().setIsActive(true);
                    }
                    else
                    {
                        robots.get(i).setY(robots.get(i).getRocket().getY() + 15);
                    }

                }
                //Else move the robot with the rocket.
                else
                {
                    robots.get(i).setY(robots.get(i).getRocket().getY() + 15);
                }
            }
            else
            {
                //If the robot is already off the rocket and is now falling at its own speed, with a parachute.
                robots.get(i).setY(robots.get(i).getY() + robots.get(i).getIncrementY());
            }
        }

    }

    public List<Drawable> setAsDrawable(List<Robot> robots) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < robots.size(); i++)
            converted.add(robots.get(i));
        return converted;
    }

    public List<Coordinate> generateCurveCoordinates(Coordinate start, Coordinate end, int bezierX, int bezierY) {
        List<Coordinate> coordinates = new ArrayList();
        for(double t=0.0;t<=1;t+=0.05)
        {
            int x = (int) (  (1-t)*(1-t)*start.getX() + 2*(1-t)*t*bezierX+t*t*end.getX());
            int y = (int) (  (1-t)*(1-t)*start.getY() + 2*(1-t)*t*bezierY+t*t*end.getY());
            coordinates.add(new Coordinate(x,y));
        }
        return coordinates;
    }

    private void moveRobotOnACurve(Robot robot) {

        double currentTime = System.currentTimeMillis();
        if(currentTime - robot.getTimeElapsed() > seconds)
        {
            Coordinate coordinate = robot.getNextJumpCoordinate();
            robot.setX(coordinate.getX());
            robot.setY(coordinate.getY());
            robot.setTimeElapsed(currentTime);
        }
    }

    private void setJumpCoordinates(Robot robot) {
        Coordinate coordinateFrom = new Coordinate(robot.getX(), robot.getY());
        Coordinate coordinateTo;
        int bezierX;
        int bezierY=0;

        //Make sure the robot jumps off the rocket towards the bigger half of the phones screen.
        if(robot.getX() > MainGamePanel.getScreenWidth() / 2)
        {
            bezierX = (int)robot.getX() - 25;
            coordinateTo = new Coordinate(robot.getX() - 50, robot.getY());
        }
        else
        {
            bezierX = (int)robot.getX() + 25;
            coordinateTo = new Coordinate(robot.getX() + 50, robot.getY());
        }
        //Get the coordinates of the curve that the robot is supposed to move through.
        robot.setJumpCoordinates(generateCurveCoordinates(coordinateFrom, coordinateTo, bezierX, bezierY));
    }

    public void removeRobots(List<Robot> robots, List<Parachute> parachutes) {
        for(int i = 0; i < robots.size(); i++)
            if(robots.get(i).getY() < 0) {
                parachutes.remove(robots.get(i).getParachute());
                robots.remove(i);
            }
    }
}
