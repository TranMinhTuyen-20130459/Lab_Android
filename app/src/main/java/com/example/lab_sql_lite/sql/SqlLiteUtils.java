
package com.example.lab_sql_lite.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab_sql_lite.entities.Lop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SqlLiteUtils {

    // kiểm tra db đã tồn tại hay chưa ?
    public static boolean checkExistDb(String nameDb) {
        String path = "/data/data/com.example.lab_sql_lite/databases";
        File file = new File(path + "/" + nameDb);
        return file.exists();
    }

    // xóa db
    public static boolean checkDeleteDb(String nameDb) {
        String path = "/data/data/com.example.lab_sql_lite/databases";
        return SQLiteDatabase.deleteDatabase(new File(path + "/" + nameDb));
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

    // kiểm tra table đã tồn tại trong db hay chưa

    public static boolean checkExistTable(SQLiteDatabase db, String nameTb) {
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + nameTb + "'";
        Cursor c = db.rawQuery(query, null);
        return c.getCount() > 0 ? true : false;
    }

    // thêm dòng mới vào bảng Lop trong database quan-ly-sinh-vien.db

    public static long doInsertRecordTableLop(String maLop, String tenLop, String siSo) {

        String path = "/data/data/com.example.lab_sql_lite/databases";
        String nameDb = "quan-ly-sinh-vien.db";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path + "/" + nameDb, null);
        try {

            if (SqlLiteUtils.checkExistTable(db, LopContract.LopEntry.TABLE_NAME) == false)
                return -2; // chưa có bảng table lops trong database

            ContentValues values = new ContentValues();
            values.put(LopContract.LopEntry.COLUMN_NAME_MALOP, maLop);
            values.put(LopContract.LopEntry.COLUMN_NAME_TENLOP, tenLop);
            values.put(LopContract.LopEntry.COLUMN_NAME_SISO, siSo);

            return db.insert(LopContract.LopEntry.TABLE_NAME, null, values);

        } finally {
            db.close();
        }


    }

    // xóa dòng của bảng Lop trong database quan-ly-sinh-vien.db
    public static long doDeleteRecordTable(String malop) {
        String path = "/data/data/com.example.lab_sql_lite/databases";
        String nameDb = "quan-ly-sinh-vien.db";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path + "/" + nameDb, null);
        try {

            if (SqlLiteUtils.checkExistTable(db, LopContract.LopEntry.TABLE_NAME) == false)
                return -2; // chưa có bảng table lops trong database

            String[] where_args = {malop};
            return db.delete(LopContract.LopEntry.TABLE_NAME, LopContract.LopEntry.COLUMN_NAME_MALOP + "=?", where_args);
        } finally {
            db.close();
        }
    }

    // cập nhật bảng Lop trong database quan-ly-sinh-vien.db
    public static long doUpdateTable(String maLop, String tenLop, String siSo) {
        String path = "/data/data/com.example.lab_sql_lite/databases";
        String nameDb = "quan-ly-sinh-vien.db";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path + "/" + nameDb, null);
        try {

            if (SqlLiteUtils.checkExistTable(db, LopContract.LopEntry.TABLE_NAME) == false)
                return -2; // chưa có bảng table lops trong database

            ContentValues values = new ContentValues();
            values.put(LopContract.LopEntry.COLUMN_NAME_TENLOP, tenLop);
            values.put(LopContract.LopEntry.COLUMN_NAME_SISO, siSo);
            return db.update(LopContract.LopEntry.TABLE_NAME, values, LopContract.LopEntry.COLUMN_NAME_MALOP + "=?", new String[]{maLop});

        } finally {
            db.close();
        }
    }

    // load danh sách lớp học trong database quan-ly-sinh-vien.db

    public static List<Lop> loadAllTableLop() {
        List<Lop> result = new ArrayList<>();
        try {

            String path = "/data/data/com.example.lab_sql_lite/databases";
            String nameDb = "quan-ly-sinh-vien.db";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(path + "/" + nameDb, null, SQLiteDatabase.OPEN_READWRITE);

            Cursor c = db.query(LopContract.LopEntry.TABLE_NAME, null, null, null, null, null, null);
            c.moveToFirst();
            while (c.isAfterLast() == false) {

                String maLop = c.getString(c.getColumnIndexOrThrow(LopContract.LopEntry.COLUMN_NAME_MALOP));
                String tenLop = c.getString(c.getColumnIndexOrThrow(LopContract.LopEntry.COLUMN_NAME_TENLOP));
                String siSo = c.getString(c.getColumnIndexOrThrow(LopContract.LopEntry.COLUMN_NAME_SISO));

                Lop lop = new Lop(maLop, tenLop, siSo);
                result.add(lop);

                c.moveToNext();
            }
            db.close();

        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

}
