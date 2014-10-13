package Physics.Play.drawableManagers;

import Physics.Play.views.MainGameView;
import Physics.Play.drawables.Bullet;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Robot;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/25/14.
 */
public class BulletManager {

    private static BulletManager b = new BulletManager();
    private boolean logEnabled = true;
    double seconds = 1000;
    private BulletManager(){
    }

    public static BulletManager getInstance( ) {
        return b;
    }

    public void createBullets(int amount, List<Bullet> bullets, MainGameView g) {
        for(int i = 0; i < amount; i++) {
            bullets.add(new Bullet(g));
        }
    }

    public void move(List<Bullet> bullets) {
        for(int i = 0; i < bullets.size(); i++)
        {
            bullets.get(i).setY(bullets.get(i).getY() + 4);
        }
    }

    public List<Drawable> setAsDrawable(List<Bullet> bullets) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < bullets.size(); i++)
            converted.add(bullets.get(i));
        return converted;
    }

    public void createBullets(List<Robot> robots, List<Bullet> bullets, MainGameView g) {
        double currentTime = System.currentTimeMillis();
        for(int i = 0; i < robots.size(); i++)
        {
            if ((currentTime - robots.get(i).getTimeOfLastBulletShot()) > seconds && robots.get(i).getY() > 0)
            {
                float x = robots.get(i).getX();
                float y = robots.get(i).getY() + robots.get(i).getHeight();
                Bullet leftBullet = new Bullet(g, x, y);
                Bullet rightBullet = new Bullet(g, x + robots.get(i).getWidth(), y);
                bullets.add(leftBullet);
                bullets.add(rightBullet);
                robots.get(i).setTimeOfLastBulletShot(currentTime);
            }
        }

    }

    public void checkForRemoval(List<Bullet> bullets) {
        for(int i = 0; i < bullets.size(); i++)
            if(bullets.get(i).isActive() == false)
                bullets.remove(i);
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("Bullet Manager :: ", print);
    }
}
