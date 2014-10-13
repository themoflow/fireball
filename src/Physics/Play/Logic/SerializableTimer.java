package Physics.Play.logic;

import Physics.Play.drawables.Drawable;

import java.io.Serializable;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class SerializableTimer extends TimerTask implements Serializable {

    private Drawable d;

    public SerializableTimer(Drawable d) {
        super();
        this.d = d;
    }

    @Override
    public void run() {
        d.switchImage();
    }
}
