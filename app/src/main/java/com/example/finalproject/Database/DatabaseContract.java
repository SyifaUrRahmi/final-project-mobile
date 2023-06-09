package com.example.finalproject.Database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "detail";
    public static final class DetailColumns implements BaseColumns {
        public static String JENIS = "jenis";
        public static String JUDUL = "judul";
        public static String WAKTU = "waktu";
        public static String SINOPSIS = "sinopsis";
        public static String POSTER = "poster";
        public static String BACKDROP = "backdrop";
        public static Integer VOTE;
    }
}
