package Physics.Play.drawables;

import java.io.Serializable;

/**
 * Created by morantornesella-brooks on 9/24/14.
 */
public class SlingShot implements Serializable {

    float leftX = 10, midX, rightX, topY, bottomY;

    public SlingShot(float midX, float rightX, float topY){
        this.midX = midX;
        this.rightX = rightX;
        this.topY = topY;
        this.bottomY = topY;
    }

    public float getLeftX() {
        return leftX;
    }

    public float getMidX() {
        return midX;
    }

    public float getRightX() {
        return rightX;
    }

    public float getTopY() {
        return topY;
    }

    public float getBottomY() {
        return bottomY;
    }

    public void setBottomY(float y) {
        bottomY = y;
    }

    public void setMidX(float x) {
        midX = x;
    }
}
