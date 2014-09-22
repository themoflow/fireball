package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.main.MainGamePanel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class Robot extends Drawable {

    private MainGamePanel gamePanel;
    private boolean isOnRocket = true;
    private static float width, height;

    public Robot(MainGamePanel g, float x, float y){
        super();
        gamePanel = g;
        Bitmap robot = BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.robot);
        setImage(robot);
        setx(x);
        sety(y);
        width = robot.getWidth();
        height = robot.getHeight();
    }

    public Robot(MainGamePanel g){
        super();
        gamePanel = g;
        Bitmap robot = BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.robot);
        setImage(robot);
        width = (robot.getWidth());
        height = (robot.getHeight());
    }

    public static float getHeight(){
        return height;
    }

    public static float getWidth(){
        return width;
    }

    public Bitmap getImg(){
        return getImage();
    }

    public void setIsActive(boolean b) {
        super.setIsActive(b);
    }

    public boolean isActive() {
        return super.isActive();
    }

    public boolean isOnRocket() {
        return isOnRocket;
    }

    public void setIsOnRocket(boolean b) {
        isOnRocket = b;
    }




}
