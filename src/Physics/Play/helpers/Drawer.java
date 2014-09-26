package Physics.Play.helpers;

import Physics.Play.drawables.*;
import android.graphics.*;

import java.util.List;

/**
 * Created by morantornesella-brooks on 9/7/14.
 */
public class Drawer {

    private static Drawer d = new Drawer();

    private Drawer() {
    }

    public static Drawer getInstance(){
        return d;
    }

    public void drawToCanvas(List<Drawable> drawables, Canvas c) {
        for(int i = 0; i < drawables.size(); i++)
            if(drawables.get(i).isActive())
                c.drawBitmap(drawables.get(i).getImage(), drawables.get(i).getX(), drawables.get(i).getY(), null);
    }

    public void backgroundColor(int color, Canvas c) {
        c.drawColor(color);
    }

    public void drawLine(float startX, float startY, float endX, float endY, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    public void drawArch(float left, float top, float right, float bottom, Canvas canvas) {
        Path path = new Path();
        path.addCircle(300, 100, 20, Path.Direction.CW);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        final RectF oval = new RectF();
        oval.set(left, top, right, bottom);
        canvas.drawArc(oval, 10, 50, true, paint);
    }
}
