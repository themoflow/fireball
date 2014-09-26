package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class ScreenBackground extends Drawable {

    private static float width, height;

    public ScreenBackground(MainGamePanel g, float x, float y){
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.screen_background));
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.screen_background).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.screen_background).getHeight();
    }

    public static float getHeight(){
        return height;
    }

    public static float getWidth(){
        return width;
    }
}
