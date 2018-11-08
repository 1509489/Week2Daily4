package com.pixelart.week2daily4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBTag";

    private static final String DATABASE_NAME = "CelebrityDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Celebrities";
    public static final String CELEBRITY_NAME ="Name";
    public static final String CELEBRITY_AGE = "Age";
    public static final String CELEBRITY_WEIGHT = "Weight";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + CELEBRITY_NAME + " TEXT PRIMARY KEY," +
                CELEBRITY_AGE + " TEXT," + CELEBRITY_WEIGHT + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCelebrity(Celebrity celebrity)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(CELEBRITY_NAME, celebrity.getName());
        values.put(CELEBRITY_AGE, celebrity.getAge());
        values.put(CELEBRITY_WEIGHT, celebrity.getWeight());
        database.insert(TABLE_NAME, null,values);
        Log.d(TAG, "addCelebrity");
    }

    public List<Celebrity> getCelebrity()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);
        List<Celebrity> celebrities = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                Celebrity celebrity = new Celebrity(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
                celebrities.add(celebrity);
            }while (cursor.moveToNext());
        }

        return celebrities;
    }

    public void deleteCelebrity(String name)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        database.delete(TABLE_NAME, CELEBRITY_NAME + "=?", new String[]{name});
    }
}
