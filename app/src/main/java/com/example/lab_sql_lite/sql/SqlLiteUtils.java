
package com.example.lab_sql_lite.sql;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;

public class SqlLiteUtils {

    // kiểm tra db đã tồn tại hay chưa
    public static boolean checkExistDb(String nameDb) {
        String path = "/data/data/com.example.lab_sql_lite/databases";
        File file = new File(path + "/" + nameDb);
        return file.exists();
    }

    // tạo mới một db
    public static void createDb(String nameDb) {

        String path = "/data/data/com.example.lab_sql_lite/databases";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        SQLiteDatabase dtb = SQLiteDatabase.openOrCreateDatabase(path + "/" + nameDb, null);
        dtb.close();

        // SQLiteDatabase dtb = SQLiteDatabase.openDatabase(path + "/" + nameDb, null, SQLiteDatabase.CREATE_IF_NECESSARY); // => tạo db mới nếu chưa có , nếu có rồi thì mở db đó
    }

    // xóa db
    public static boolean checkDeleteDb(String nameDb) {
        String path = "/data/data/com.example.lab_sql_lite/databases";
        return SQLiteDatabase.deleteDatabase(new File(path + "/" + nameDb));
    }

    // xóa tất cả db
    public static void deleteAllDb() {
        String path = "/data/data/com.example.lab_sql_lite/databases";
        File folder = new File(path);
        if (folder.exists()) {
            File[] files = folder.listFiles();
            for (File f : files) {
                SQLiteDatabase.deleteDatabase(f);
            }
        }
    }

}
