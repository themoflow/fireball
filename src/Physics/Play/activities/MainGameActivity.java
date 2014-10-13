package Physics.Play.activities;

import Physics.Play.views.MainGameView;
import Physics.Play.database.DatabaseManager;
import Physics.Play.model.GameState;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.*;

public class MainGameActivity extends Activity {

    private AlertDialog.Builder builder;
    private Bundle reset;
    private Looper looper;
    private String displayText;
    private MainGameView mainGameView;
    private DatabaseManager dbm;
    private boolean logEnabled = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(":::: MyActivity.java - ", "onCreate() called :::: ");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dbm = new DatabaseManager(this);
        builder = new AlertDialog.Builder(this);
        looper = Looper.getMainLooper();
        String gameType = getIntent().getExtras().getString("gameType");
        mainGameView = new MainGameView(this);
        if(gameType.equals("continue")) {
            GameState gameState = dbm.getSavedGameState();
            if (gameState != null)
                mainGameView.setGameState(gameState);
        }
        setContentView(mainGameView);
    }

    @Override
    protected void onStart() {
        Log.i(":::: MyActivity.java - ", "onStart() called :::: ");
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(":::: MyActivity.java - ", "onPause() called :::: ");
    }

    @Override
    public void onStop(){
        super.onStop();
        log("onStop() called");
        if(!mainGameView.getGameState().isGameOver()) {
            log("isGAmeOver() = false");
            dbm.saveGameState(mainGameView.getGameState());
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        log("onDestroy() called :::: ");
    }



    public void showDialog(String text){
        displayText = text;
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
        });
    }



    private void log(String print) {
        if(logEnabled != false)
            Log.i(":::: MyActivity.java - ", print + " ::::");
    }
}