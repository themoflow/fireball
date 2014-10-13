package Physics.Play.core;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private MainGameView gamePanel;
    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder, MainGameView gamePanel) {
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
            //If getCityHits() = 5 game is over.
            /*if(MainGamePanel.GAME_OVER) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {

                }
            }*/

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
