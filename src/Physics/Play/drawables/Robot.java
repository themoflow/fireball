package Physics.Play.drawables;

import Physics.Play.bitmaps.RobotBitmaps;
import Physics.Play.views.MainGameView;
import Physics.Play.model.Coordinate;
import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class Robot extends Drawable {

    private boolean isOnRocket = true;
    private boolean isJumping = false;
    private Parachute parachute;
    private double timeOfLastBulletShot = 0;
    private float incrementY = 0.4f;
    private List<Coordinate> jumpCoordinates = null;
    private double timeElapsed = 0;
    private int imageIndex = 0;

    public Robot(MainGameView g){
        super();
        setWidth(RobotBitmaps.getImage(0).getWidth());
        setHeight(RobotBitmaps.getImage(0).getHeight());
    }

    public Robot(MainGameView g, float x, float y){
        super();
        setWidth(RobotBitmaps.getImage(0).getWidth());
        setHeight(RobotBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
    }


    public boolean isOnRocket() {
        return isOnRocket;
    }

    public void setIsOnRocket(boolean b) {
        isOnRocket = b;
    }

    public void setIsJumping(boolean j) {
        isJumping = j;
        imageIndex = 1;
        setWidth(RobotBitmaps.getImage(imageIndex).getWidth());
        setHeight(RobotBitmaps.getImage(imageIndex).getHeight());
    }

    @Override
    public Bitmap getImage() {
        return RobotBitmaps.getImage(imageIndex);
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setTimeOfLastBulletShot(double time) {
        timeOfLastBulletShot = time;
    }

    public double getTimeOfLastBulletShot() {
        return timeOfLastBulletShot;
    }

    public float getIncrementY() {
        return incrementY;
    }

    public void setIncrementY(float y){
        incrementY = y;
    }

    public List<Coordinate> getJumpCoordinates() {
        return jumpCoordinates;
    }

    public void setJumpCoordinates(List<Coordinate> coordinates) {
        jumpCoordinates = coordinates;
    }

    public Coordinate getNextJumpCoordinate() {
        if(jumpCoordinates.size() == 1) {
            setX(jumpCoordinates.get(0).getX());
            setY(jumpCoordinates.get(0).getY());
        }
        Coordinate coordinate = jumpCoordinates.get(0);
        jumpCoordinates.remove(0);
        if(jumpCoordinates.size() == 0)
            isJumping = false;

        return coordinate;
    }

    public void setTimeElapsed(double t) {
        timeElapsed = t;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public boolean isReadyToJump() {
        if(getY() > 100)
            return true;
        else
            return false;
    }

    public void setParachute(Parachute p) {
        parachute = p;
    }

    public Parachute getParachute() {
        return parachute;
    }

    public Bitmap getRobotStandingBitmap() {
        return RobotBitmaps.getImage(1);
    }
}
