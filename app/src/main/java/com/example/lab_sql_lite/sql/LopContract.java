package com.example.lab_sql_lite.sql;

import android.provider.BaseColumns;

public final class LopContract {

    private LopContract(){}

    /* Inner class that defines the table contents */
        public static class LopEntry implements BaseColumns{
        public static final String TABLE_NAME = "lops";
        public static final String COLUMN_NAME_MALOP = "malop";
        public static final String COLUMN_NAME_TENLOP = "tenlop";
        public static final String COLUMN_NAME_SISO = "siso";
    }


}
