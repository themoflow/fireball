package Physics.Play.serializables;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by morantornesella-brooks on 10/17/14.
 */
public class SerializableThread extends Thread implements Serializable {

    private Drawable d;
    private boolean logEnabled = true;

    public SerializableThread(Drawable d) {
        super();
        this.d = d;
    }

    @Override
    public void run() {

        while(Fireball.stopAnimation.get(d.getCount()).equals("run"))
        {
            log("in loop = " + (d.getCount()) + " :::: " + Fireball.stopAnimation.get(d.getCount()));
            try
            {
                d.switchImage();
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                log("InterruptedException was thrown during a call to animationThread.sleep(50), msg = " + e.getMessage());
                e.printStackTrace();
            }

        }
        log("loop has been exited from = " + (d.getCount()) + " :::: " + Fireball.stopAnimation.get(d.getCount()));
    }

    public void log(String print) {
        if(logEnabled)
            Log.i(":::: SerializableThread.java -> ", print + " ::::");

    }

}
