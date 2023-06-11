package com.example.finalproject.Database;

import static com.example.finalproject.Database.DatabaseContract.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DetailHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
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
    public int deleteById(String id_) {
        return database.delete(DATABASE_TABLE, DatabaseContract.DetailColumns.ID_ + " LIKE '"
                + id_ + "%'", null);
    }
    public void hapus() {
    SQLiteDatabase db = databaseHelper.getReadableDatabase();;
    db.delete(TABLE_NAME,null,null);
    db.close();
    }
    public boolean isFav(String id_) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + DatabaseContract.DetailColumns.ID_ + " LIKE '" + id_ + "%'";
        Cursor cursor = db.rawQuery(query, null);
        boolean isFav = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isFav;
    }
    public static ArrayList<Detail> cari(String text){
        ArrayList<Detail> hasil = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + DatabaseContract.DetailColumns.JUDUL + " LIKE '" + text + "%'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Detail detail = getNotesFromCursor(cursor);
                hasil.add(detail);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return hasil;
    }
    private static Detail getNotesFromCursor(Cursor cursor) {
        Detail detail = new Detail();
        detail.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns._ID)));
        detail.setId_(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.ID_)));
        detail.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.JUDUL)));
        detail.setVote(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.VOTE)));
        detail.setWaktu(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.WAKTU)));
        detail.setJenis(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.JENIS)));
        detail.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.POSTER)));
        detail.setBackdrop(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.BACKDROP)));
        detail.setSinopsis(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.SINOPSIS)));
        return detail;
    }

}
