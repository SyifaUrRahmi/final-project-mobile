package com.example.finalproject.Database;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Detail> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Detail> details = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns._ID));
            String jenis =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.JENIS));
            String judul =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.JUDUL));
            String waktu =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.WAKTU));
            String sinopsis =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.SINOPSIS));
            String poster =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.POSTER));
            String backdrop =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DetailColumns.BACKDROP));
            String vote =
                    cursor.getString(cursor.getColumnIndexOrThrow(String.valueOf(DatabaseContract.DetailColumns.VOTE)));
            details.add(new Detail(id, jenis, judul, waktu, sinopsis, poster, backdrop, vote));
        }
        return details;
    }
}
