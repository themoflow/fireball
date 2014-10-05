package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.core.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class ScreenBackground extends Drawable {


    public ScreenBackground(MainGamePanel g, float x, float y){
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.screen_background));
        setWidth(BitmapFactory.decodeResource(g.getResources(), R.drawable.screen_background).getWidth());
        setHeight(BitmapFactory.decodeResource(g.getResources(), R.drawable.screen_background).getHeight());
        setX(x);
        setY(y);
    }

    public static void initializeStaticMembers(MainGamePanel g) {

    }

}
