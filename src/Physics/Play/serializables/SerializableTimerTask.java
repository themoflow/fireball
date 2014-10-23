package Physics.Play.serializables;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import android.util.Log;

import java.io.Serializable;
import java.util.TimerTask;

/**
 * Created by morantornesella-brooks on 10/11/14.
 */
public class SerializableTimerTask extends TimerTask implements Serializable {

    private Drawable d;
    private boolean logEnabled = true;

    public SerializableTimerTask(Drawable d) {
        super();
        this.d = d;
    }

    @Override
    public void run() {

        if(d.getClass() == Fireball.class)
        {

            Fireball f = (Fireball) d;
            log("fireball timer is still running, isTimerOver = " + f.isTimerOver());
            if(!f.isTimerOver())
                f.switchImage();
        }
        else {

            d.switchImage();
        }
    }

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: SerializableTimerTask.java -> ", print + " ::::");
    }
}
