package Physics.Play.drawableManagers;

import Physics.Play.drawables.Fireball;
import Physics.Play.drawables.SlingShot;

import java.util.List;

/**
 * Created by morantornesella-brooks on 9/26/14.
 */
public class SlingShotManager {

    private static SlingShotManager s = new SlingShotManager();

    private SlingShotManager(){}

    public static SlingShotManager getInstance( ) {
        return s;
    }

    public void move(List<Fireball> fireballs, SlingShot slingShot) {
        if(fireballs.size() > 0) {
            float x = fireballs.get(fireballs.size() - 1).getX() + (Fireball.getWidth() / 2);
            float y = fireballs.get(fireballs.size() - 1).getY() + (Fireball.getHeight() / 2);
            slingShot.setMidX(x);
            slingShot.setBottomY(y);
        }
    }
}
