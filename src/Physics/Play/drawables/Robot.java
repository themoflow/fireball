package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class Robot extends Drawable {

    private boolean isOnRocket = true;
    private boolean destroyed = false;
    private static float width, height;
    private Rocket rocket;
    private double timeOfLastBulletShot = 0;
    public Robot(MainGamePanel g){
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.robot));
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot).getHeight();
    }

    public static float getHeight() {
        return height;
    }

    public static float getWidth(){
        return width;
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

    public void setDestroyed(boolean b) {
        destroyed = b;
    }

    public boolean hasBeenDestroyed() {
        return destroyed;
    }

    public void setTimeOfLastBulletShot(double time) {
        timeOfLastBulletShot = time;
    }

    public double getTimeOfLastBulletShot() {
        return timeOfLastBulletShot;
    }


}
