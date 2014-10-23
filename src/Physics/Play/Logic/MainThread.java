package Physics.Play.logic;

import Physics.Play.views.MainGameView;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;


public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private MainGameView gamePanel;
    private volatile boolean running;
    private boolean logEnabled = false;

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
        while (running)
        {
            Canvas canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                gamePanel.onDraw(canvas);
            }
            if(canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
   }

    private void log(String print) {
        if(logEnabled)
            Log.i(":::: MainThread.java -> ", print + " ::::");
    }

}
