package Physics.Play.model;

import java.io.Serializable;

/**
 * Created by morantornesella-brooks on 9/26/14.
 */
public class Coordinate implements Serializable {

    private float x, y;

    public Coordinate(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        x = x;
    }

    public void setY(float y) {
        y = y;
    }
}

