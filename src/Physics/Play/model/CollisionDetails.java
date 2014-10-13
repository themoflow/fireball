package Physics.Play.model;

import Physics.Play.drawables.Drawable;

import java.io.Serializable;

/**
 * Created by morantornesella-brooks on 10/6/14.
 */
public class CollisionDetails implements Serializable {

    private float x;
    private float y;
    private Drawable d1;
    private Drawable d2;

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDrawable1(Drawable d) {
        this.d1 = d;
    }

    public void setDrawable2(Drawable d) {
        this.d2 = d;
    }

    public float  getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Drawable getDrawable1() {
        return d1;
    }

    public Drawable getDrawable2() {
        return d2;
    }
}
