package Physics.Play;

import android.graphics.Bitmap;

/**
 * Created by morantornesella-brooks on 9/5/14.
 */
public class Drawable {

    private float x,y;
    private static float width, height;
    private Bitmap image;

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

    public static float getWidth(){
        return width;
    }

    public static float getHeight(){
        return height;
    }

    public static void setWidth(float w){ width = w; }

    public static void setHeight(float h){
        height = h;
    }

    public Bitmap getImage() { return image; }

    public void setImage(Bitmap image) {  this.image = image; }
}
