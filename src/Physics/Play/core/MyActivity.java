package Physics.Play.core;


import Physics.Play.R;
import Physics.Play.db.DatabaseManager;
import Physics.Play.model.GameState;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.*;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;


public class MyActivity extends Activity {

    private AlertDialog.Builder builder;
    private Bundle reset;
    private Looper looper;
    private String displayText;
    private MainGamePanel mainGamePanel;
    private DatabaseManager mDbHelper;
    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(":::: MyActivity.java - ", "onCreate() called :::: ");
        super.onCreate(savedInstanceState);
        mDbHelper = new DatabaseManager(this);
        db = mDbHelper.getReadableDatabase();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        builder = new AlertDialog.Builder(this);
        Button[] b =  new Button[2];
        b[0] = (Button)findViewById(R.id.btnTwo);
        b[1] = (Button)findViewById(R.id.btnThree);
        looper = Looper.getMainLooper();
        mainGamePanel = new MainGamePanel(this);
        setContentView(mainGamePanel);
        //setListeners(b,this);
    }

    public void showDialog(String text){

        displayText = text;

        MyActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                builder.setMessage(displayText).setCancelable(false).setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setContentView(new MainGamePanel(MyActivity.this));
                    }
                })
                        .setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MyActivity.this.finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



    }

    public void setListeners(Button[] b,final MyActivity a){

        b[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });

        b[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainGamePanel game = new MainGamePanel(a);
                setContentView(game);
            }
        });

    }//end setListeners().

    @Override
    public void onPause() {
        super.onPause();
        Log.i(":::: MyActivity.java - ", "onPause() called :::: ");

    }

    @Override
    public void onStop(){
        super.onStop();
        log("onStop() called");
        GameState gameState = mainGamePanel.getGameState();
        if(isGameStateSaved())
        {
            //Update existing column with current game state.
            updateDatabase(gameState);
        }
        else
        {
            //Create new column with current game state.
            insertNewRow(gameState);
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        log("onDestroy() called :::: ");
    }

    private boolean isGameStateSaved() {
        Cursor d = db.rawQuery("SELECT * FROM " + mDbHelper.TABLE_NAME, null);
        log("isGameStateSaved() - row count = " + d.getCount());
        if(d.getCount() > 0)
            return true;
        else
            return false;
    }

    private void updateDatabase(GameState gameState) {
        byte[] gameStateInBytes = serializeGameState(gameState);
        ContentValues values = new ContentValues();
        values.put(mDbHelper.COLUMN_NAME, gameStateInBytes);
        db.update(mDbHelper.TABLE_NAME, values, null, null);
        Cursor d = db.rawQuery("SELECT * FROM " + mDbHelper.TABLE_NAME, null);
        d.moveToFirst();
        log("updateDatabase() - row count = " + d.getBlob(1));
    }

    private void insertNewRow(GameState gameState) {
        byte[] gameStateInBytes = serializeGameState(gameState);
        ContentValues values = new ContentValues();
        values.put(mDbHelper.COLUMN_NAME, gameStateInBytes);
        long newRowId = db.insert(mDbHelper.TABLE_NAME, null, values);
        log("new row id = " + newRowId + " ::::");
    }

    private byte[] serializeGameState(GameState gameState) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(gameState);
            return baos.toByteArray();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            log("serializeGameState() - IOException thrown");
        }
        return null;
    }

    private void log(String print) {
        Log.i(":::: MyActivity.java - ", print + " ::::");
    }
}