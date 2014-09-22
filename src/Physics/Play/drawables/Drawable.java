package Physics.Play.drawables;

import android.graphics.Bitmap;

/**
 * Created by morantornesella-brooks on 9/5/14.
 */
public class Drawable {

    private float x,y;
    private Bitmap image;
    private boolean isActive = true;

    public Drawable() {
    }

    public float getx(){
        return x;
    }

    public float gety(){
        return y;
    }

    public void setx(float x){
        this.x = x;
    }

    public void sety(float y){
        this.y = y;
    }

    public Bitmap getImage() { return image; }

    public void setImage(Bitmap image) {  this.image = image; }

    public void setIsActive(boolean b) {
        isActive = b;
    }

    public boolean isActive() {
        return isActive;
    }


}
