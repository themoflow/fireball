package Physics.Play.core;

import Physics.Play.drawables.City;
import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;
    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {

        loop: while (running)
        {
            Canvas canvas = null;
            //If getCityHits() = 10 game is over.
            if(City.getCityHits() == 10) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {

                }
            }

            canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                gamePanel.onDraw(canvas);
            }
            if(canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
   }

}
