package Physics.Play.activities;

import Physics.Play.drawables.Fireball;
import Physics.Play.logic.SavedContext;
import Physics.Play.views.MainGameView;
import Physics.Play.database.DatabaseManager;
import Physics.Play.model.GameState;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.*;
import android.widget.PopupWindow;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainGameActivity extends Activity {

    private AlertDialog.Builder builder;
    private Looper looper;
    private MainGameView mainGameView;
    private DatabaseManager dbm;
    private boolean logEnabled = true;
    private PopupWindow popupWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        log("onCreate() called");
        super.onCreate(savedInstanceState);
        SavedContext.setContext(getApplicationContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dbm = new DatabaseManager(this);
        builder = new AlertDialog.Builder(this);
        //looper = Looper.getMainLooper();
        //looper.prepare();
        //looper.loop();
        String gameType = getIntent().getExtras().getString("gameType");
        mainGameView = new MainGameView(this);

        if(gameType.equals("continue"))
        {
            GameState gameState = dbm.getSavedGameState();
            if (gameState != null) {
                log("stopAnimation list size = " + gameState.getFireballs().get(0).amount);
                mainGameView.setGameState(gameState);
            }
            dbm.closeDB();
        }
        setContentView(mainGameView);
    }

    @Override
    protected void onStart() {
        log("onStart() called");
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        log("onPause() called");
        List<Fireball> fireballs = mainGameView.getGameState().getFireballs();
        for(int i = 0; i < fireballs.size(); i++)
            fireballs.get(i).cancelTimer();

    }

    @Override
    public void onStop(){
        super.onStop();
        log("onStop() called");
        List<Fireball> fireballs = mainGameView.getGameState().getFireballs();
        for(int i = 0; i < fireballs.size(); i++)
        {
            fireballs.get(i).cancelTimer();
        }
        if(!mainGameView.getGameState().isGameOver()) {
            log("stopAnimation list size = " + mainGameView.getGameState().getFireballs().get(0).amount);
            dbm.saveGameState(mainGameView.getGameState());
        }
        dbm.closeDB();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        log("onDestroy() called :::: ");
    }

    public void createPopup() {
        /*LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.game_over, null, false);
        popupWindow = new PopupWindow(layout, 300, 300, true);
        popupWindow.showAtLocation(layout, Gravity.NO_GRAVITY, 0, 0);*/
    }

    public void closePopup() {
        /*popupWindow.dismiss();*/
    }

    public void displayGameOverMessage(String text){
        MainGameActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                    log("dismissing pop up");
                    Intent intent = new Intent(MainGameActivity.this, StartScreenActivity.class);
                    intent.putExtra("gameOver","true");
                    MainGameActivity.this.startActivity(intent);
                    dbm.clearSavedGameState();
                    MainGameActivity.this.finish();
                    }
                };//end timertask();
                timer.schedule(timerTask, 1000L);
            }//end run()
        });
        /*displayText = text;
        MainGameActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                builder.setMessage(displayText).setCancelable(false).setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Save null gamestate.
                        dbm.clearSavedGameState();
                        mainGameView = new MainGameView(MainGameActivity.this);
                        setContentView(mainGameView);
                    }
                })
                        .setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //save null game state.
                                dbm.clearSavedGameState();
                                MainGameActivity.this.finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });*/
    }

    private void log(String print) {
        if(logEnabled != false)
            Log.i(":::: MainGameActivity.java - ", print + " ::::");
    }
}