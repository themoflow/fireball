package Physics.Play.helpers;

import java.util.List;

import Physics.Play.core.MainGameView;
import Physics.Play.model.CollisionDetails;
import Physics.Play.model.Coordinate;
import Physics.Play.model.drawables.*;
import android.graphics.Rect;
import android.graphics.Color;
import android.util.Log;


public class CollisionDetector {

    private static CollisionDetector d = new CollisionDetector();
    private boolean logEnabled = true;

    private CollisionDetector() {
    }

    public static CollisionDetector getInstance() {
        return d;
    }

    public CollisionDetails detectCollision(List<Drawable> drawables1, List<Drawable> drawables2){

        CollisionDetails collisionDetails = new CollisionDetails();
        for(int A = 0; A < drawables1.size(); A++)
        {
            Drawable d1 = drawables1.get(A);
            Rect d1Rec = new Rect((int)d1.getX(), (int)d1.getY(), (int)(d1.getX() + d1.getWidth()), (int)(d1.getY() + d1.getHeight()));

            for(int B = 0; B < drawables2.size(); B++)
            {
                Drawable d2 = drawables2.get(B);
                Rect d2Rec = new Rect( (int)d2.getX(), (int)d2.getY(), (int)(d2.getX() + d2.getWidth()), (int)(d2.getY() + d2.getHeight()));

                if(Rect.intersects(d1Rec, d2Rec) )
                {
                    Rect collisionBounds = getCollisionBounds(d1Rec, d2Rec);
                    for (int k = collisionBounds.left; k < collisionBounds.right; k++)
                    {
                        for (int L = collisionBounds.top; L < collisionBounds.bottom; L++)
                        {
                            int d1Pixel = getBitmapPixel(d1, k, L);
                            int d2Pixel = getBitmapPixel(d2, k, L);

                            if(isFilled(d1Pixel) && isFilled(d2Pixel))
                            {
                                collisionDetails.setX(drawables2.get(B).getX());
                                collisionDetails.setY(drawables2.get(B).getY());
                                collisionDetails.setDrawable1(drawables1.get(A));
                                collisionDetails.setDrawable2(drawables2.get(B));
                                return collisionDetails ;
                            }
                        }
                    }
                }
            }

        }
        return null;

    }  //end detectCollision.

    public boolean willRobotCollideWithAnotherRobot(List<Robot> robots, MainGameView g) {
        for (int A = 0; A < robots.size(); A++) {
            if (robots.get(A).isOnRocket() && robots.get(A).getJumpCoordinates() != null) {
                List<Coordinate> CC = robots.get(A).getJumpCoordinates();
                Coordinate coordinate = CC.get(CC.size() - 1);
                Rect robotRec = new Rect((int) coordinate.getX(), (int) coordinate.getY(), (int) (coordinate.getX() + robots.get(A).getWidth()), (int) (coordinate.getY() + robots.get(A).getHeight()));
                for (int B = 0; B < robots.size(); B++) {
                    Robot robot2 = robots.get(B);
                    if (robot2.getY() > 0 && A != B) {
                        Rect robot2Rec = new Rect((int) robot2.getX(), (int) robot2.getY(), (int) (robot2.getX() + robot2.getWidth()), (int) (robot2.getY() + robot2.getHeight()));
                        if (Rect.intersects(robotRec, robot2Rec)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private int getBitmapPixel(Drawable drawable, int i, int j) {
        return drawable.getImage().getPixel(i - (int)drawable.getX(), j - (int)drawable.getY());
    }

     private static Rect getCollisionBounds(Rect rect1, Rect rect2) {
        int left = Math.max(rect1.left, rect2.left);
        int top = Math.max(rect1.top, rect2.top);
        int right = Math.min(rect1.right, rect2.right);
        int bottom = Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }

    private static boolean isFilled(int pixel) {
         return pixel != Color.TRANSPARENT;
    }

    private void deActivate(Drawable d) {
        if(!(d.getClass() == City.class) && !(d.getClass() == Shield.class))
        {
            log(d.getClass() + " has been deactivated");
            d.setIsActive(false);
        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("CollisionDetector - ",print);
    }

}
