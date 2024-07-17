package com.example.mypens.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //insert
    public void insertData(String title, String subtitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
    }

    public void readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME, null)) {

            if (cursor.getCount() == 0) {
                Log.d("TAG", "No data");
            } else {
                while (cursor.moveToNext()) {
                    String title = cursor.getString(1);
                    String subtitle = cursor.getString(2);
                    Log.d("TAG", "Title: " + title + " Subtitle: " + subtitle);
                }
            }
        }
        //        db.query(FeedReaderContract.FeedEntry.TABLE_NAME, null, null, null, null, null, "$_ID ASC");
    }

    public void deleteData(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?", new String[]{title});
    }
    
    //update
    public void updateData(String title, String subtitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
        db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?", new String[]{title});
    }


}



