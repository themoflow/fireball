package Physics.Play.Logic;

import Physics.Play.drawables.*;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.List;

/**
 * Created by morantornesella-brooks on 9/7/14.
 */
public class Drawer {

    public Drawer() {
    }

    public void drawToCanvas(List<Drawable> drawables, Canvas c) {
        List<Fireball> fireballList = null;
        Fireball fireball = null;
        List<Rocket> rocketList = null;
        Rocket rocket = null;
        City city = null;
        Explosion explosion = null;
        color(Color.BLACK, c);
        for(int i = 0; i < drawables.size(); i++)
        {
            if(drawables.get(i).getClass() == Fireball.class)
            {
                fireball = (Fireball) drawables.get(i);
                fireballList = fireball.getArray();
            }
            else if(drawables.get(i).getClass() == Rocket.class)
            {
                rocket = (Rocket) drawables.get(i);
                rocketList = rocket.getArray();
            }
            else if(drawables.get(i).getClass() == City.class) {
                city = (City) drawables.get(i);
            }
            else if(drawables.get(i).getClass() == Explosion.class) {
                explosion = (Explosion) drawables.get(i);
            }
        }
        for(int i = 0; i < fireballList.size(); i++)
        {
            c.drawBitmap(fireballList.get(i).getImage(), fireballList.get(i).getx(), fireballList.get(i).gety(), null);
        }
        for(int i = 0; i < rocketList.size(); i++)
        {
            c.drawBitmap(rocketList.get(i).getImage(), rocketList.get(i).getx(), rocketList.get(i).gety(), null);
        }
        c.drawBitmap(city.getImage(), city.getx(), city.gety(), null);
        if(explosion.isActive()) {
            c.drawBitmap(explosion.getImage(), explosion.getx(), explosion.gety(), null);
            explosion.setIsActive(false);
        }
    }

    private void color(int color, Canvas c) {
        c.drawColor(color);
    }
}
