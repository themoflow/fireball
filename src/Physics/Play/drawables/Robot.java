package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import Physics.Play.helpers.Coordinate;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class Robot extends Drawable {

    private boolean isOnRocket = true;
    private boolean isJumping = false;
    private Rocket rocket;
    private Parachute parachute;
    private double timeOfLastBulletShot = 0;
    private float incrementY = 0.4f;
    private List<Coordinate> jumpCoordinates = null;
    private double timeElapsed = 0;
    private Bitmap[] bitmaps = new Bitmap[2];

    public Robot(MainGamePanel g){
        super();
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_sitting);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_standing);
        setImage(bitmaps[0]);
        setWidth(bitmaps[0].getWidth());
        setHeight(bitmaps[0].getHeight());
    }

    public Robot(MainGamePanel g, float x, float y){
        super();
        bitmaps[0] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_sitting);
        bitmaps[1] = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_standing);
        setImage(bitmaps[0]);
        setWidth(bitmaps[0].getWidth());
        setHeight(bitmaps[0].getHeight());
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {

    }

    public void setRobotJumpingImage() {
        setImage(bitmaps[1]);
    }

    public boolean isOnRocket() {
        return isOnRocket;
    }

    public void setIsOnRocket(boolean b) {
        isOnRocket = b;
    }

    public void setRocket(Rocket r) {
        rocket = r;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setIsJumping(boolean j) {
        isJumping = j;
        setWidth(bitmaps[1].getWidth());
        setHeight(bitmaps[1].getHeight());
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
        return bitmaps[1];
    }
}
