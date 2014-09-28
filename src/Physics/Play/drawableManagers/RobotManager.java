package Physics.Play.drawableManagers;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.GreenDot;
import Physics.Play.drawables.Robot;
import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.Rocket;
import Physics.Play.helpers.Coordinate;
import android.util.Log;

import java.util.*;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class RobotManager {

    private MainGamePanel gamePanel;
    private double seconds = 10;

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
        {
            if(robots.get(i).isJumping())
            {
                moveRobotOnACurve(robots.get(i));
            }
            else if(robots.get(i).isOnRocket())
            {
                if(robots.get(i).isReadyToJump())
                {
                    robots.get(i).setIsJumping(true);
                    robots.get(i).setRobotJumpingImage();
                    robots.get(i).setIsOnRocket(false);
                    robots.get(i).getRocket().setHasRobot(false);
                    jumpRobotOffRocket(robots.get(i));
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
                robots.get(i).setY(robots.get(i).getY() + robots.get(i).getAdd());
            }
        }

    }

    public List<Drawable> setAsDrawable(List<Robot> robots) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < robots.size(); i++)
            converted.add(robots.get(i));
        return converted;
    }

    /*public List<Coordinate> generateCurve(Coordinate cFrom, Coordinate cTo, float pRadius, float pMinDistance, boolean shortest, boolean side)
    {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();

        // Calculate the middle of the two given points.
        Coordinate mCoordinate = new Coordinate(cFrom.getX() + cTo.getX(), cFrom.getY() + cTo.getY());
        mCoordinate.setX(mCoordinate.getX() / 2.0f);
        mCoordinate.setY(mCoordinate.getY() / 2.0f);

        // Calculate the distance between the two points
        float xDiff = cTo.getX() - cFrom.getX();
        float yDiff = cTo.getY() - cFrom.getY();
        float distance = (float) Math.sqrt(xDiff * xDiff + yDiff * yDiff);

        if (pRadius * 2.0f < distance) {
            throw new IllegalArgumentException("The radius is too small! The given points wont fall on the circle.");
        }

        // Calculate the middle of the expected curve.
        float factor = (float) Math.sqrt((pRadius * pRadius) / ((cTo.getX() - cFrom.getX()) * (cTo.getX() - cFrom.getX()) + (cTo.getY() - cFrom.getY()) * (cTo.getY() - cFrom.getY())) - 0.25f);
        Coordinate circleMiddlePoint = new Coordinate(0, 0);
        if (side) {
            circleMiddlePoint.setX(0.5f * (cFrom.getX() + cTo.getX()) + factor * (cTo.getY() - cFrom.getY()));
            circleMiddlePoint.setY(0.5f * (cFrom.getY() + cTo.getY()) + factor * (cFrom.getX() - cTo.getX()));
        } else {
            circleMiddlePoint.setX(0.5f * (cFrom.getX() + cTo.getX()) - factor * (cTo.getY() - cFrom.getY()));
            circleMiddlePoint.setY(0.5f * (cFrom.getY() + cTo.getY()) - factor * (cFrom.getX() - cTo.getX()));
        }

        // Calculate the two reference angles
        float angle1 = (float) Math.atan2(cFrom.getY() - circleMiddlePoint.getY(), cFrom.getX() - circleMiddlePoint.getX());
        float angle2 = (float) Math.atan2(cTo.getY() - circleMiddlePoint.getY(), cTo.getX() - circleMiddlePoint.getX());

        // Calculate the step.
        float step = pMinDistance / pRadius;

        // Swap them if needed
        if (angle1 > angle2) {
            float temp = angle1;
            angle1 = angle2;
            angle2 = temp;

        }
        boolean flipped = false;
        if (!shortest) {
            if (angle2 - angle1 < Math.PI) {
                float temp = angle1;
                angle1 = angle2;
                angle2 = temp;
                angle2 += Math.PI * 2.0f;
                flipped = true;
            }
        }
        for (float f = angle1; f < angle2; f += step) {
            Coordinate p = new Coordinate((float) Math.cos(f) * pRadius + circleMiddlePoint.getX(), (float) Math.sin(f) * pRadius + circleMiddlePoint.getY());
            coordinates.add(p);
        }
        Log.i("RobotManager - ","coordinates size = " + coordinates.size());
        if (flipped ^ side) {
            coordinates.add(cFrom);
        } else {
            coordinates.add(cTo);
        }

        return coordinates;
    }*/

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
            Coordinate coordinate = robot.getNextCurvedCoordinate();
            robot.setX(coordinate.getX());
            robot.setY(coordinate.getY());
            robot.setTimeElapsed(currentTime);
        }
    }

    private void jumpRobotOffRocket(Robot robot) {
        Coordinate coordinateFrom = new Coordinate(robot.getX(), robot.getY());
        Coordinate coordinateTo;
        //int startX=100;
        //int startY=70;
        //int endX=70;
        //int endY=75;
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
        robot.setCurveCoordinates(generateCurveCoordinates(coordinateFrom, coordinateTo, bezierX, bezierY));
    }
}
