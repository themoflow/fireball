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
    private static float sittingWidth, sittingHeight;
    private static float standingWidth, standingHeight;
    private Rocket rocket;
    private Parachute parachute;
    private double timeOfLastBulletShot = 0;
    private float incrementY = 0.4f;
    private List<Coordinate> jumpCoordinates = null;
    private double timeElapsed = 0;
    private List<Bitmap> bitmaps = new ArrayList();
    private float currentWidth;
    private float currentHeight;

    public Robot(MainGamePanel g){
        super();
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_sitting));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_standing));
        setImage(bitmaps.get(0));
        currentWidth = bitmaps.get(0).getWidth();
        currentHeight = bitmaps.get(0).getHeight();
    }

    public Robot(MainGamePanel g, float x, float y){
        super();
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_sitting));
        bitmaps.add(BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_standing));
        setImage(bitmaps.get(1));
        currentWidth = bitmaps.get(1).getWidth();
        currentHeight = bitmaps.get(1).getHeight();
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        sittingWidth = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_sitting).getWidth();
        sittingHeight = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_sitting).getHeight();
        standingWidth = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_standing).getWidth();
        standingHeight = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot_standing).getHeight();
    }

    public void setRobotJumpingImage() {
        setImage(bitmaps.get(1));
    }

    public float getCurrentWidth() {
        return currentWidth;
    }

    public float getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentWidth(float w) {
       currentWidth = w;
    }

    public void setCurrentHeight(float h) {
        currentHeight = h;
    }

    public static float getHeight() {

        return sittingHeight;
    }

    public static float getWidth(){

        return sittingWidth;
    }

    public static float getStandingHeight() {
        return standingHeight;
    }

    public static float getStandingWidth(){
        return standingWidth;
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
        currentWidth = standingWidth;
        currentHeight = standingHeight;
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
        return bitmaps.get(1);
    }
}
