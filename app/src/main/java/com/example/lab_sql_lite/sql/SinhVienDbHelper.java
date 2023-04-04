package com.example.lab_sql_lite.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SinhVienDbHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + SinhVienContract.SinhVienEntry.TABLE_NAME + "("
            + SinhVienContract.SinhVienEntry.COLUMN_NAME_MASV + " TEXT PRIMARY KEY, "
            + SinhVienContract.SinhVienEntry.COLUMN_NAME_TENSV + " TEXT, "
            + SinhVienContract.SinhVienEntry.COLUMN_NAME_MALOP + " TEXT NOT NULL CONSTRAINT " + SinhVienContract.SinhVienEntry.COLUMN_NAME_MALOP
            +" REFERENCES " + LopContract.LopEntry.TABLE_NAME + "(" + LopContract.LopEntry.COLUMN_NAME_MALOP + ")" + " ON DELETE CASCADE "
            + ")";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + SinhVienContract.SinhVienEntry.TABLE_NAME;

    public SinhVienDbHelper(@Nullable Context context) {
        super(context, InforDatabase.DATABASE_NAME, null, InforDatabase.DATABASE_VERSION);
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
