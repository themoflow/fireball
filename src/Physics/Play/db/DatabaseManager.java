package Physics.Play.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by morantornesella-brooks on 10/10/14.
 */
public final class DatabaseManager extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "savedGameState";
    public static final String COLUMN_NAME = "gameState";
    public static final String ID = "id";
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Database.db";
    private static final String SQL_CREATE_ENTRIES =  "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " BLOB" + ")";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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

}