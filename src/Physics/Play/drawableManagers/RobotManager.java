package Physics.Play.drawableManagers;

import Physics.Play.R;
import Physics.Play.views.MainGameView;
import Physics.Play.drawables.*;
import Physics.Play.logic.CollisionDetector;
import Physics.Play.model.Coordinate;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.*;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class RobotManager {

    private double seconds = 10;
    private static RobotManager r = new RobotManager();
    private boolean logEnabled = false;

    private RobotManager() {

    }

    public static RobotManager getInstance() {
        return r;
    }

    public Robot createRobot(List<Robot> robots, MainGameView g) {
        Robot robot = new Robot(g);
        robots.add(robot);
        return robot;
    }

    public void addRobotToRocket(Robot robot, Rocket rocket) {
            float rocketWidthCenter = rocket.getWidth() / 2;
            float robotWidthCenter = robot.getWidth() / 2;
            float robotOffset = robotWidthCenter - rocketWidthCenter;
            float x = rocket.getX() - robotOffset;
            robot.setX(x);
            robot.setY(rocket.getY());
            rocket.addDrawable(robot);
            robot.addDrawable(rocket);
    }

    public void move(List<Robot> robots, List<Parachute> parachutes, CollisionDetector collisionDetector, MainGameView g) {

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
                        robots.get(i).setIsOnRocket(false);
                        float pWidth = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_1).getWidth();
                        float pHeight = BitmapFactory.decodeResource(g.getResources(), R.drawable.parachute_1).getHeight();
                        float parachuteCenterX = pWidth / 2;
                        float robotCenterX = robots.get(i).getWidth() / 2;
                        Parachute parachute = new Parachute(g, (robots.get(i).getX() - (parachuteCenterX - robotCenterX)), (robots.get(i).getY() - pHeight));
                        robots.get(i).getDrawable(Rocket.class).removeDrawable(Robot.class);
                        robots.get(i).removeDrawable(Rocket.class);
                        robots.get(i).addDrawable(parachute);
                        parachute.addDrawable(robots.get(i));
                        parachutes.add(parachute);

                    }
                    else
                    {
                        robots.get(i).setY(robots.get(i).getDrawable(Rocket.class).getY() + 15);
                    }

                }
                //Else move the robot with the rocket.
                else
                {
                    robots.get(i).setY(robots.get(i).getDrawable(Rocket.class).getY() + 15);
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
        if(robot.getX() > MainGameView.getScreenWidth() / 2)
        {
            bezierX = (int)robot.getX() - 25;
            coordinateTo = new Coordinate(robot.getX() - 50, robot.getY() + 75);
        }
        else
        {
            bezierX = (int)robot.getX() + 25;
            coordinateTo = new Coordinate(robot.getX() + 50, robot.getY() + 75);
        }
        //Get the coordinates of the curve that the robot is supposed to move through.
        robot.setJumpCoordinates(generateCurveCoordinates(coordinateFrom, coordinateTo, bezierX, bezierY));
    }

    public void removeRobots(List<Robot> robots) {
        for(int i = 0; i < robots.size(); i++)
            if(robots.get(i).getY() < 0) {
                log("ROBOT REMOVED y = " + robots.get(i).getY());
                robots.remove(i);
            }
    }

    public void checkForRemoval(List<Robot> robots) {
        for(int i = 0; i < robots.size(); i++)
            if(robots.get(i).isActive() == false)
                robots.remove(i);
    }

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: RobotManager.java :::: ", print);
    }
}
