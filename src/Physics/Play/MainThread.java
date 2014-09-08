
package Physics.Play;


import android.graphics.Canvas;
import android.os.Looper;
import android.view.SurfaceHolder;


public class MainThread extends Thread {


    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;
    private boolean running;
    private Collision collision;
    private Fireball fireball;


    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel, Fireball fireList, Rocket rocket) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        fireball = fireList;
        collision = new Collision(rocket, fireList, gamePanel);
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

            }
            if (canvas != null)
                surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

}
