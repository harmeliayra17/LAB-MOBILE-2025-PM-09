package com.example.labpertemuan8.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "notes";

    public static final class NotesColumn implements BaseColumns {
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
    }

}
