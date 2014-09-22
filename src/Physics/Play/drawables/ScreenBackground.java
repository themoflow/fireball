package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.main.MainGamePanel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class ScreenBackground extends Drawable {

    private MainGamePanel gamePanel;

    public ScreenBackground(MainGamePanel g, float x, float y){
        gamePanel = g;
        setImage(BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.screen_background));
        setx(x);
        sety(y);
    }

    public float getImgHeight(){
        return getImage().getHeight();
    }
    public Bitmap getImg(){
        return getImage();
    }
}
