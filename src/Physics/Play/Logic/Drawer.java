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

    public void backgroundColor(int color, Canvas c) {
        c.drawColor(color);
    }
}
