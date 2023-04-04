package com.example.lab_sql_lite.sql;

import android.database.sqlite.SQLiteDatabase;

import androidx.navigation.ui.NavigationUI;

public class Database {

    public static final String DATABASE_PATH = "/data/data/com.example.lab_sql_lite/databases";
    public static final String DATABASE_NAME = "quan-ly-sinh-vien.db";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public static Database INSTANCE = null;
    public static SQLiteDatabase dbConnect;

    public Database() {
        dbConnect = SQLiteDatabase.openDatabase(DATABASE_PATH + "/" + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public static Database getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new Database();
        return INSTANCE;
    }
}
