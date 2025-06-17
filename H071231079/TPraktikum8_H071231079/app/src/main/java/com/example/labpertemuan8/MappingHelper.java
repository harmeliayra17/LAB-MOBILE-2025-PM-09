package com.example.labpertemuan8;

import android.database.Cursor;

import com.example.labpertemuan8.database.DatabaseContract;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Notes> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Notes> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumn._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumn.TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumn.DESCRIPTION));
            String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumn.CREATED_AT));
            String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumn.UPDATED_AT));

            notes.add(new Notes(id, title, description, createdAt, updatedAt));
        }
        return notes;
    }
}
