package Physics.Play.database;

import Physics.Play.model.GameState;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.*;

/**
 * Created by morantornesella-brooks on 10/10/14.
 */
public final class DatabaseManager extends SQLiteOpenHelper {

    private boolean logEnabled = false;
    private static final String TABLE_NAME = "savedGameState";
    private static final String COLUMN_NAME = "gameState";
    private static final String ID = "id";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Database.db";
    private static final String SQL_CREATE_ENTRIES =  "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " BLOB" + ")";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;
    private SQLiteDatabase db;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        db = getReadableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i(":::: DatabaseManager.java - ", "onCreate() was called ::::");
        db.execSQL(SQL_CREATE_ENTRIES);
        ContentValues values = new ContentValues();
        values.putNull(COLUMN_NAME);
        db.insert(TABLE_NAME, null, values);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(":::: DatabaseManager.java - ", "onUpgrade() was called ::::");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(":::: DatabaseManager.java - ", "onDowngrade() was called ::::");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void saveGameState(GameState gameState) {
        byte[] gameStateInBytes = serializeGameState(gameState);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, gameStateInBytes);
        db.update(TABLE_NAME, values, null, null);
        Cursor d = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        d.moveToFirst();
        log("updateDatabase() - row count = " + d.getCount());
    }

    public GameState getSavedGameState() {
        Cursor d = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (d.getCount() > 0)
        {
            d.moveToFirst();
            byte[] gameStateInBytes = d.getBlob(1);
            return deSerializeGameState(gameStateInBytes);
        }
        else
        {
            return null;
        }
    }

    public void clearSavedGameState() {
        ContentValues values = new ContentValues();
        values.putNull(COLUMN_NAME);
        db.update(TABLE_NAME, values, null, null);
    }

    public boolean isGameStateSaved() {
        Cursor d = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(d.getCount() > 0)
        {
            log("isGameStateSaved(), count is > 0");
            d.moveToFirst();
            if(d.getBlob(1) == null) {
                log("isGameStateSaved(), getBlob() is null");
                return false;
            }
            else
            {
                log("d.getBlob() = " + d.getBlob(1));
                return true;
            }

        }
        else
        {
            log("isGameStateSaved(), getCount() is not > 0");
            return false;
        }
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

    private GameState deSerializeGameState(byte[] b) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            ObjectInputStream ois = new ObjectInputStream(bais);
            GameState gameState = (GameState)ois.readObject();
            return gameState;
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            log("serializeGameState() - IOException thrown");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            log("serializeGameState() - IOException thrown");
        }
        return null;
    }

    public void closeDB() {
        db.close();
    }

    private void log(String print) {
        if(logEnabled)
            Log.i("DatabaseManager.java - ",print);
    }


}