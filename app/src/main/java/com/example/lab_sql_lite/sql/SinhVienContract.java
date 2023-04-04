package com.example.lab_sql_lite.sql;

import android.provider.BaseColumns;

public final class SinhVienContract {

    private SinhVienContract(){}

    /* Inner class that defines the table contents */
    public static class SinhVienEntry implements BaseColumns{

        public static final String TABLE_NAME = "sinhviens";
        public static final String COLUMN_NAME_MASV = "masv";
        public static final String COLUMN_NAME_TENSV = "tensv";
        public static final String COLUMN_NAME_MALOP = "malop";
    }
}
