package com.example.finalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finalproject.Data_API.MovieResponse;

import java.util.ArrayList;

public class DetailHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile DetailHelper INSTANCE;
    public DetailHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public static DetailHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DetailHelper(context);
                }
            }
        }
        return INSTANCE;
    }
    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }
    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }
    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.DetailColumns._ID + " DESC"
        );
    }
    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.DetailColumns._ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }
    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }
    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DatabaseContract.DetailColumns._ID
                + " = ?", new String[]{id});
    }
    public int deleteByJudul(String judul) {
        return database.delete(DATABASE_TABLE, DatabaseContract.DetailColumns.JUDUL + " LIKE '"
                + judul + "%'", null);
    }
    public boolean isFav(String judul) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.TABLE_NAME + " WHERE " + DatabaseContract.DetailColumns.JUDUL + " LIKE '" + judul + "%'";
        Cursor cursor = db.rawQuery(query, null);
        boolean isFav = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isFav;
    }
}
