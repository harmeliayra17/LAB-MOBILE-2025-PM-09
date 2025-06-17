package com.example.labpertemuan8.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotesHelper {
    public static String TABLE_NAME = DatabaseContract.TABLE_NAME;
    public static DatabaseHelper databaseHelper;
    public static SQLiteDatabase sqLiteDatabase;
    public static volatile NotesHelper INSTANCE;

    public static NotesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public NotesHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }


    public void close() {
        databaseHelper.close();
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }


    public Cursor queryAll() {
        return sqLiteDatabase.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.NotesColumn._ID + " ASC"
        );
    }


    public Cursor queryById(String id) {
        return sqLiteDatabase.query(
                TABLE_NAME,
                null,
                DatabaseContract.NotesColumn._ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }


    public long insert(ContentValues values) {
        String now = getCurrentTimestamp();
        values.put(DatabaseContract.NotesColumn.CREATED_AT, now);
        values.put(DatabaseContract.NotesColumn.UPDATED_AT, now);
        return sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public int update(String id, ContentValues values) {
        String now = getCurrentTimestamp();
        values.put(DatabaseContract.NotesColumn.UPDATED_AT, now);
        return sqLiteDatabase.update(TABLE_NAME, values, DatabaseContract.NotesColumn._ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return sqLiteDatabase.delete(TABLE_NAME, DatabaseContract.NotesColumn._ID + " = ?",
                new String[]{id});
    }

    // method buat dapatkan timestamp sekarang
    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

}
