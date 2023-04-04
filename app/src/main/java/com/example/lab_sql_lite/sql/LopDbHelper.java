package com.example.lab_sql_lite.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LopDbHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ LopContract.LopEntry.TABLE_NAME + "("
                                                                    + LopContract.LopEntry.COLUMN_NAME_MALOP + " TEXT PRIMARY KEY, "
                                                                    + LopContract.LopEntry.COLUMN_NAME_TENLOP + " TEXT, "
                                                                    + LopContract.LopEntry.COLUMN_NAME_SISO + " TEXT "
                                                                    + ")";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + LopContract.LopEntry.TABLE_NAME;

    public LopDbHelper(@Nullable Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
