package Physics.Play.model.drawables;

import Physics.Play.bitmaps.ScreenBackgroundBitmaps;
import Physics.Play.core.MainGameView;
import android.graphics.Bitmap;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class ScreenBackground extends Drawable {

    private int imageIndex = 0;

    public ScreenBackground(MainGameView g, float x, float y){
        super();
        setWidth(ScreenBackgroundBitmaps.getImage(0).getWidth());
        setHeight(ScreenBackgroundBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
    }

    @Override
    public Bitmap getImage() {
        return ScreenBackgroundBitmaps.getImage(imageIndex);
    }

}
