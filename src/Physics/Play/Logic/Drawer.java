package Physics.Play.Logic;

import Physics.Play.drawables.*;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.List;

/**
 * Created by morantornesella-brooks on 9/7/14.
 */
public class Drawer {

    public Drawer() {
    }

    public void drawToCanvas(List<Drawable> drawables, Canvas c) {
        for(int i = 0; i < drawables.size(); i++)
            c.drawBitmap(drawables.get(i).getImage(), drawables.get(i).getx(), drawables.get(i).gety(), null);
    }

    public void drawFireballs(List<Fireball> fireballs, Canvas c) {
        List<Fireball> fireballList = null;
        Fireball fireball = null;
        for(int i = 0; i < fireballs.size(); i++)
        {
            if(fireballs.get(i).getClass() == Fireball.class)
            {
                fireball = (Fireball) fireballs.get(i);
                fireballList = fireball.getArray();
            }
        }
        if(fireballList != null) {
            for (int i = 0; i < fireballList.size(); i++) {
                c.drawBitmap(fireballList.get(i).getImage(), fireballList.get(i).getx(), fireballList.get(i).gety(), null);
            }
        }
    }

    public void backgroundColor(int color, Canvas c) {
        c.drawColor(color);
    }
}
