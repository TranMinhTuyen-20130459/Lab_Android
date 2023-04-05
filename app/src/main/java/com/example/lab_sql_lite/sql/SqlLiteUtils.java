
package com.example.lab_sql_lite.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab_sql_lite.entities.Lop;
import com.example.lab_sql_lite.entities.SinhVien;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SqlLiteUtils {

    // kiểm tra db đã tồn tại hay chưa ?
    public static boolean checkExistDb(String nameDb) {
        File file = new File(Database.DATABASE_PATH + "/" + nameDb);
        return file.exists();
    }

    // xóa db
    public static boolean checkDeleteDb(String nameDb) {
        return SQLiteDatabase.deleteDatabase(new File(Database.DATABASE_PATH + "/" + nameDb));
    }

    // tạo mới một db
    public static void createDb(String nameDb) {

        File file = new File(Database.DATABASE_PATH);
        if (!file.exists()) {
            file.mkdir();
        }

        SQLiteDatabase dtb = SQLiteDatabase.openOrCreateDatabase(Database.DATABASE_PATH + "/" + nameDb, null);
        dtb.close();

        // SQLiteDatabase dtb = SQLiteDatabase.openDatabase(path + "/" + nameDb, null, SQLiteDatabase.CREATE_IF_NECESSARY); // => tạo db mới nếu chưa có , nếu có rồi thì mở db đó
    }

    // xóa tất cả db
    public static void deleteAllDb() {

        File folder = new File(Database.DATABASE_PATH);
        if (folder.exists()) {
            File[] files = folder.listFiles();
            for (File f : files) {
                SQLiteDatabase.deleteDatabase(f);
            }
        }
    }

    // kiểm tra table đã tồn tại trong db hay chưa

    public static boolean checkExistTable(SQLiteDatabase dbConnect, String nameTb) {
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + nameTb + "'";
        Cursor c = dbConnect.rawQuery(query, null);
        return c.getCount() > 0 ? true : false;
    }

    // thêm dòng mới vào bảng Lop trong database quan-ly-sinh-vien.db

    public static long doInsertRecordTableLop(SQLiteDatabase dbConnect, String maLop, String tenLop, String siSo) {

        try {

            if (SqlLiteUtils.checkExistTable(dbConnect, LopContract.LopEntry.TABLE_NAME) == false)
                return -2; // chưa có bảng table lops trong database

            ContentValues values = new ContentValues();
            values.put(LopContract.LopEntry.COLUMN_NAME_MALOP, maLop);
            values.put(LopContract.LopEntry.COLUMN_NAME_TENLOP, tenLop);
            values.put(LopContract.LopEntry.COLUMN_NAME_SISO, siSo);

            return dbConnect.insert(LopContract.LopEntry.TABLE_NAME, null, values);

        } catch (Exception e) {
            return -1;
        } finally {
            dbConnect.close();
        }


    }

    // xóa dòng của bảng Lop trong database quan-ly-sinh-vien.db
    public static long doDeleteRecordTable(SQLiteDatabase dbConnect, String malop) {
        try {

            if (SqlLiteUtils.checkExistTable(dbConnect, LopContract.LopEntry.TABLE_NAME) == false)
                return -2; // chưa có bảng table lops trong database

            String[] where_args = {malop};
            return dbConnect.delete(LopContract.LopEntry.TABLE_NAME, LopContract.LopEntry.COLUMN_NAME_MALOP + "=?", where_args);

        } catch (Exception e) {
            return -1;
        } finally {
            dbConnect.close();
        }
    }

    // cập nhật bảng Lop trong database quan-ly-sinh-vien.db
    public static long doUpdateTable(SQLiteDatabase dbConnect, String maLop, String tenLop, String siSo) {

        try {

            if (SqlLiteUtils.checkExistTable(dbConnect, LopContract.LopEntry.TABLE_NAME) == false)
                return -2; // chưa có bảng table lops trong database

            ContentValues values = new ContentValues();
            values.put(LopContract.LopEntry.COLUMN_NAME_TENLOP, tenLop);
            values.put(LopContract.LopEntry.COLUMN_NAME_SISO, siSo);
            return dbConnect.update(LopContract.LopEntry.TABLE_NAME, values, LopContract.LopEntry.COLUMN_NAME_MALOP + "=?", new String[]{maLop});

        } catch (Exception e) {
            return -1;
        } finally {
            dbConnect.close();
        }
    }

    // load danh sách lớp học trong database quan-ly-sinh-vien.db

    public static List<Lop> loadAllTableLop(SQLiteDatabase dbConnect) {
        List<Lop> result = new ArrayList<>();
        try {

            Cursor c = dbConnect.query(LopContract.LopEntry.TABLE_NAME, null, null, null, null, null, null);
            c.moveToFirst();
            while (c.isAfterLast() == false) {

                String maLop = c.getString(c.getColumnIndexOrThrow(LopContract.LopEntry.COLUMN_NAME_MALOP));
                String tenLop = c.getString(c.getColumnIndexOrThrow(LopContract.LopEntry.COLUMN_NAME_TENLOP));
                String siSo = c.getString(c.getColumnIndexOrThrow(LopContract.LopEntry.COLUMN_NAME_SISO));

                Lop lop = new Lop(maLop, tenLop, siSo);
                result.add(lop);

                c.moveToNext();
            }
            dbConnect.close();

        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    // thêm dòng mới vào bảng SinhVien trong database quan-ly-sinh-vien.db
    public static long doInsertRecordTableSinhVien(SQLiteDatabase dbConnect, SinhVien sv) {
        try {

            if (SqlLiteUtils.checkExistTable(dbConnect, SinhVienContract.SinhVienEntry.TABLE_NAME) == false)
                return -2; // chưa có bảng table sinhviens trong database

            ContentValues values = new ContentValues();
            values.put(SinhVienContract.SinhVienEntry.COLUMN_NAME_MASV, sv.getMaSV());
            values.put(SinhVienContract.SinhVienEntry.COLUMN_NAME_TENSV, sv.getTenSV());
            values.put(SinhVienContract.SinhVienEntry.COLUMN_NAME_MALOP, sv.getMaLop());

            return dbConnect.insert(SinhVienContract.SinhVienEntry.TABLE_NAME, null, values);

        } catch (Exception e) {
            return -1;
        } finally {
            dbConnect.close();
        }
    }

    // load SinhVien theo MaLop
    public static List<SinhVien> loadSinhVienByMaLop(SQLiteDatabase dbConnect, String maLop) {
        List<SinhVien> rs = new ArrayList<>();
        try {
            Cursor c = dbConnect.query(SinhVienContract.SinhVienEntry.TABLE_NAME, null,
                    SinhVienContract.SinhVienEntry.COLUMN_NAME_MALOP + "=?", new String[]{maLop}, null, null, null);
            c.moveToFirst();
            while (c.isAfterLast() == false) {

                String MASV = c.getString(c.getColumnIndexOrThrow(SinhVienContract.SinhVienEntry.COLUMN_NAME_MASV));
                String TenSV = c.getString(c.getColumnIndexOrThrow(SinhVienContract.SinhVienEntry.COLUMN_NAME_TENSV));
                String MaLop = c.getString(c.getColumnIndexOrThrow(SinhVienContract.SinhVienEntry.COLUMN_NAME_MALOP));
                SinhVien sv = new SinhVien(MASV, TenSV, MaLop);
                rs.add(sv);

                c.moveToNext();
            }
            dbConnect.close();
        } catch (Exception e) {
            rs = new ArrayList<>();
        }
        return rs;
    }

}
