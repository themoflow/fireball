
package Physics.Play;


import android.graphics.Canvas;
import android.os.Looper;
import android.view.SurfaceHolder;


/**
 * @author impaler
 *
 * The Main thread which contains the game loop. The thread must have access to 
 * the surface view and holder to trigger events every game tick.
 */
public class MainThread extends Thread {


    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;
    private boolean running;
    private Collision collision;
    private FireballsList fireballList;



    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel, FireballsList fireList, RocketList rocketList, ShipRocketList srl) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        fireballList = fireList;
        collision = new Collision(rocketList,fireList, srl, gamePanel);


    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {

        Canvas canvas;
  loop: while (running)
        {
            if(gamePanel.gameOver())
            {
                try
                {
                    Thread.sleep(200);
                    canvas = null;
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized(surfaceHolder)
                    {
                        this.gamePanel.onDraw(canvas);

                    }
                    if (canvas != null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    continue loop;
                }
                catch(Exception e)
                {

                }
            }
            float[] coords = collision.fireBallColided();
            float[] coords2 = collision.rocketCollidedWithCity();
            float[] coords3 = collision.shipRocketCollisionWithCity();


            canvas = null;
            canvas = this.surfaceHolder.lockCanvas();
            synchronized(surfaceHolder)
            {
                this.gamePanel.onDraw(canvas);

                if(!(coords == null))
                    this.gamePanel.explosion(true, coords);

                if(!(coords2 == null))
                {
                    this.gamePanel.addHitToCity();
                    this.gamePanel.explosion(true, coords2);
                }

                if(!(coords3 == null))
                {
                    this.gamePanel.addHitToCity();
                    this.gamePanel.explosion(true, coords3);
                }

            }
            if (canvas != null)
                surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

}
