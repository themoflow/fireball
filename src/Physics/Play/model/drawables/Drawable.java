package Physics.Play.model.drawables;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/5/14.
 */
public abstract class Drawable implements Serializable {

    private float x, y;
    private  float width, height;
    private boolean isActive = true;
    private List<Drawable> drawables = new ArrayList();

    public Drawable() {}

    public Bitmap getImage() {
        return null;
    }

    public void switchImage() {

    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public void setIsActive(boolean b) {
        isActive = b;
    }

    public boolean isActive() {
        return isActive;
    }

    public float getHeight(){
        return height;
    }

    public float getWidth(){
        return width;
    }

    public void setHeight(float h){
        height = h;
    }

    public void setWidth(float w){
        width = w;
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }

    public void addDrawable(Drawable d) {
        drawables.add(d);
    }

    public void removeDrawable(Class rClass) {
        for(int i = 0; i < drawables.size(); i++)
            if(drawables.get(i).getClass() == rClass) {
                drawables.remove(i);
            }
    }

    public Drawable getDrawable(Class gClass) {
        for(int i = 0; i < drawables.size(); i++)
            if(drawables.get(i).getClass() == gClass)
                return drawables.get(i);
        return null;
    }


}
