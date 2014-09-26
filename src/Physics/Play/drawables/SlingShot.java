package Physics.Play.drawables;

/**
 * Created by morantornesella-brooks on 9/24/14.
 */
public class SlingShot extends Drawable {

    float radius, left, top, right, bottom;

    public SlingShot(float rad, float l, float t, float r, float b){
        radius = rad;
        left = l;
        top = t;
        right = r;
        bottom = b;
    }

    public float getRadius() {
        return radius;
    }

    public float getLeft() {
        return left;
    }

    public float getTop() {
        return top;
    }

    public float getRight() {
        return right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setRadius(float r) {
        radius = r;
    }

    public void setLeft(float l) {
        left = l;
    }

    public void setTop(float t) {
        radius = t;
    }

    public void setRight(float r) {
        radius = r;
    }

    public void setBottom(float b) {
        radius = b;
    }

}
