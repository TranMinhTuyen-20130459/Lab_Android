package com.example.lab_sql_lite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab_sql_lite.R;
import com.example.lab_sql_lite.dialogs.CustomDialogCreateDb;
import com.example.lab_sql_lite.dialogs.CustomDialogDeleteDb;
import com.example.lab_sql_lite.dialogs.CustomDialog;
import com.example.lab_sql_lite.entities.Lop;
import com.example.lab_sql_lite.entities.SinhVien;
import com.example.lab_sql_lite.sql.Database;
import com.example.lab_sql_lite.sql.LopDbHelper;
import com.example.lab_sql_lite.sql.SinhVienDbHelper;
import com.example.lab_sql_lite.sql.SqlLiteUtils;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btCreateDb, btDeleteDb, btCreateTb, btDeleteTb, btInsertRowTbLop,
            btDeleteRowTbLop, btUpdateRowTbLop, btQueryDataTbLop, btInsertStudent, btQueryStudent;
    private EditText edtNameTableDel, edtNameMALOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btCreateDb = findViewById(R.id.bt_create_db);
        this.btDeleteDb = findViewById(R.id.bt_delete_db);
        this.btCreateTb = findViewById(R.id.bt_create_tb);
        this.btDeleteTb = findViewById(R.id.bt_delete_tb);
        this.btInsertRowTbLop = findViewById(R.id.bt_insert_tblop);
        this.btDeleteRowTbLop = findViewById(R.id.bt_delete_row);
        this.btUpdateRowTbLop = findViewById(R.id.bt_update_row);
        this.btQueryDataTbLop = findViewById(R.id.bt_query_table);
        this.btInsertStudent = findViewById(R.id.bt_insert_object);
        this.btQueryStudent = findViewById(R.id.bt_query_object);
        this.edtNameTableDel = findViewById(R.id.edt_name_tb_delete);
        this.edtNameMALOP = findViewById(R.id.edt_malop);

        btCreateDb.setOnClickListener(view -> clickButtonCreateDb());
        btDeleteDb.setOnClickListener(view -> clickButtonDeleteDb());
        btCreateTb.setOnClickListener(view -> clickButtonCreateTb());
        btDeleteTb.setOnClickListener(view -> clickButtonDeleteTb());
        btInsertRowTbLop.setOnClickListener(view -> clickButtonInsertRowTbLop());
        btDeleteRowTbLop.setOnClickListener(view -> clickButtonDeleteRowTbLop());
        btUpdateRowTbLop.setOnClickListener(view -> clickButtonUpdateRowTbLop());
        btQueryDataTbLop.setOnClickListener(view -> clickButtonQueryDataTbLop());
        btInsertStudent.setOnClickListener(view -> clickButtonInsertStudent());
        btQueryStudent.setOnClickListener(view -> clickButtonQueryStudent());
        edtNameTableDel.setOnClickListener(view -> clickButtonDeleteTb());

    }

    public void clickButtonCreateDb() {

        android.app.Dialog dialogCreateDb = new CustomDialogCreateDb(this);
        dialogCreateDb.show();
    }

    public void clickButtonDeleteDb() {
        android.app.Dialog dialogDeleteDb = new CustomDialogDeleteDb(this);
        dialogDeleteDb.show();
    }

    public void clickButtonCreateTb() {

        String nameDb = "quan-ly-sinh-vien.db";
        if (SqlLiteUtils.checkExistDb(nameDb) == false) {
            Toast.makeText(this, "database quan-ly-sinh-vien chưa có trong hệ thống, hãy tạo mới ^.^", Toast.LENGTH_SHORT).show();
        } else {

            String path = "/data/data/com.example.lab_sql_lite/databases";
            SQLiteDatabase dtb = SQLiteDatabase.openOrCreateDatabase(path + "/" + nameDb, null);
            LopDbHelper lopDbHelper = new LopDbHelper(this);
            SinhVienDbHelper svDbHelper = new SinhVienDbHelper(this);
            lopDbHelper.onUpgrade(dtb, 1, 2);
            svDbHelper.onUpgrade(dtb, 1, 2);
            Toast.makeText(this, "Tạo bảng Lop và bảng SinhVien thành công ^.^", Toast.LENGTH_SHORT).show();
            dtb.close();

        }

    }

    public void clickButtonDeleteTb() {

        String nameTbDel = edtNameTableDel.getText().toString();
        String msg = "";
        if (nameTbDel == null || nameTbDel.isEmpty()) {
            msg = "Please enter name table to delete !!!";
        } else {

            if (SqlLiteUtils.checkExistDb(Database.DATABASE_NAME) == false) {
                msg = "database quan-ly-sinh-vien chưa có trong hệ thống, hãy tạo mới ^.^";
            } else {

                SQLiteDatabase dtb = new Database().dbConnect;
                switch (nameTbDel) {
                    case "Lop":
                        dtb.execSQL(LopDbHelper.SQL_DELETE_ENTRIES);
                        msg = "Table Lop đã được xóa ^.^";
                        edtNameTableDel.setText("");
                        break;
                    case "SinhVien":
                        dtb.execSQL(SinhVienDbHelper.SQL_DELETE_ENTRIES);
                        msg = "Table SinhVien đã được xóa";
                        edtNameTableDel.setText("");
                        break;
                    default:
                        msg = "Table " + nameTbDel + " không có trong database quan-ly-sinh-vien !!!";
                }
                dtb.close();
            }
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void clickButtonInsertRowTbLop() {

        Dialog customDialog = new CustomDialog(this, 1);
        customDialog.show();
    }

    public void clickButtonDeleteRowTbLop() {

        Dialog customDialog = new CustomDialog(this, 2);
        customDialog.show();
    }

    public void clickButtonUpdateRowTbLop() {
        Dialog customDialog = new CustomDialog(this, 3);
        customDialog.show();
    }

    public void clickButtonQueryDataTbLop() {

        if (SqlLiteUtils.checkExistDb(Database.DATABASE_NAME) == false) {
            Toast.makeText(this, "chưa có database quan-ly-sinh-vien trong hệ thống !!! , hãy tạo lại", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase dbConnect = new Database().dbConnect;
            List<Lop> lops = SqlLiteUtils.loadAllTableLop(dbConnect);
            if (lops.size() > 0) {
                Intent intent = new Intent(this, DataTableLop.class);
                intent.putExtra("listLop", (Serializable) lops);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Chưa có dữ liệu của table Lop !!! hãy thêm vào", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void clickButtonInsertStudent() {

        Dialog customDialog = new CustomDialog(this, 4);
        customDialog.show();

    }

    public void clickButtonQueryStudent() {
        String msg = "";
        String maLop = edtNameMALOP.getText().toString();
        if (maLop.isEmpty()) {
            msg = "Bạn hãy cho biết mã lớp ^.^";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {

            if (SqlLiteUtils.checkExistDb(Database.DATABASE_NAME) == false) {
                msg = "chưa có database quan-ly-sinh-vien trong hệ thống !!! , hãy tạo lại";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            } else {
                SQLiteDatabase dbConnect = new Database().dbConnect;
                List<SinhVien> list = SqlLiteUtils.loadSinhVienByMaLop(dbConnect, maLop);

                if (list.size() > 0) {
                    Intent intent = new Intent(this, DataTableSinhVien.class);
                    intent.putExtra("listSinhVien", (Serializable) list);
                    startActivity(intent);
                    edtNameMALOP.setText("");
                } else {
                    msg = "Chưa có dữ liệu của table SinhVien !!! hãy thêm vào";
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }

            }

        }

    }

}