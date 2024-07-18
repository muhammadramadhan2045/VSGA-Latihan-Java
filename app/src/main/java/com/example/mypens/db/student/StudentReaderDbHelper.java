package com.example.mypens.db.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentReaderDbHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StudentReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentReaderContract.StudentEntry.TABLE_NAME + " (" +
                    StudentReaderContract.StudentEntry._ID + " INTEGER PRIMARY KEY," +
                    StudentReaderContract.StudentEntry.COLUMN_NAME_NAME + " TEXT," +
                    StudentReaderContract.StudentEntry.COLUMN_NAME_BIRTH + " TEXT," +
                    StudentReaderContract.StudentEntry.COLUMN_NAME_ADDRESS + " TEXT,)" ;
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentReaderContract.StudentEntry.TABLE_NAME;

 public StudentReaderDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int odlVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertData(String id, String name, String birth,String gender, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentReaderContract.StudentEntry.COLUMN_NAME_ID, id);
        values.put(StudentReaderContract.StudentEntry.COLUMN_NAME_NAME, name);
        values.put(StudentReaderContract.StudentEntry.COLUMN_NAME_BIRTH, birth);
        values.put(StudentReaderContract.StudentEntry.COLUMN_NAME_GENDER, gender);
        values.put(StudentReaderContract.StudentEntry.COLUMN_NAME_ADDRESS, address);
        db.insert(StudentReaderContract.StudentEntry.TABLE_NAME, null, values);
    }
}
