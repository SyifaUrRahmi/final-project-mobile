package com.example.finalproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Detail.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_DETAIL =
            String.format(
                    "CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT,"
                            + " %s TEXT,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s INTEGER)",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.DetailColumns._ID,
                    DatabaseContract.DetailColumns.JENIS,
                    DatabaseContract.DetailColumns.JUDUL,
                    DatabaseContract.DetailColumns.WAKTU,
                    DatabaseContract.DetailColumns.SINOPSIS,
                    DatabaseContract.DetailColumns.POSTER,
                    DatabaseContract.DetailColumns.BACKDROP,
                    DatabaseContract.DetailColumns.VOTE

            );


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
