package com.example.labpertemuan8.database;

import static com.example.labpertemuan8.database.NotesHelper.TABLE_NAME;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Notes.db";

    public static final int DATABASE_VERSION = 3;

    private static final String SQL_CREATE_TABLE_NOTES =
            String.format(
                    "CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL)",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.NotesColumn._ID,
                    DatabaseContract.NotesColumn.TITLE,
                    DatabaseContract.NotesColumn.DESCRIPTION,
                    DatabaseContract.NotesColumn.CREATED_AT,
                    DatabaseContract.NotesColumn.UPDATED_AT
            );

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Cursor searchNotesByTitle(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_NAME,
                null,
                DatabaseContract.NotesColumn.TITLE + " LIKE ?",
                new String[]{"%" + query + "%"},
                null,
                null,
                null
        );
    }

}
