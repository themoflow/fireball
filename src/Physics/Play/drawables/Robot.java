package Physics.Play.drawables;

import Physics.Play.R;
import Physics.Play.main.MainGamePanel;
import android.graphics.BitmapFactory;

/**
 * Created by morantornesella-brooks on 9/21/14.
 */
public class Robot extends Drawable {

    private boolean isOnRocket = true;
    private static float width, height;

    public Robot(MainGamePanel g){
        super();
        setImage(BitmapFactory.decodeResource(g.getResources(), R.drawable.robot));
    }

    public static void initializeStaticMembers(MainGamePanel g) {
        width = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot).getWidth();
        height = BitmapFactory.decodeResource(g.getResources(), R.drawable.robot).getHeight();
    }

    public static float getHeight() {
        return height;
    }

    public static float getWidth(){
        return width;
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
