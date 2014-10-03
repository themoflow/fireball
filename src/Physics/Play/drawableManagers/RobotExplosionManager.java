package Physics.Play.drawableManagers;

import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.RocketExplosion;
import Physics.Play.drawables.RobotExplosion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 10/1/14.
 */
public class RobotExplosionManager {

    private static RobotExplosionManager e = new RobotExplosionManager();

    private RobotExplosionManager(){}

    public static RobotExplosionManager getInstance( ) {
        return e;
    }

    public List<RocketExplosion> createRobotExplosions(int amount, MainGamePanel g, List<RocketExplosion> explosions) {
        for(int i = 0; i < amount; i++) {
            explosions.add(new RocketExplosion(g));
        }
        return explosions;
    }

    public List<RobotExplosion> createRobotExplosion(MainGamePanel g, List<RobotExplosion> robotExplosions, float x, float y) {
        y = y - 75;
        robotExplosions.add(new RobotExplosion(g, x, y));
        return robotExplosions;
    }

    public List<Drawable> setAsDrawable(List<RobotExplosion> robotExplosions) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < robotExplosions.size(); i++)
            converted.add(robotExplosions.get(i));
        return converted;
    }

    public void setCoordinates(float x, float y, List<RocketExplosion> explosions) {
        explosions.get(0).setX(x);
        explosions.get(0).setY(y);
    }

    public void setIsActive(boolean b, List<RocketExplosion> explosions) {
        explosions.get(0).setIsActive(b);
    }

    public void checkForRemoval(List<RobotExplosion> robotExplosions) {
        for(int i = 0; i < robotExplosions.size(); i++)
        {
            if(robotExplosions.get(i).hasExploded())
                robotExplosions.remove(i);
        }
    }
}
