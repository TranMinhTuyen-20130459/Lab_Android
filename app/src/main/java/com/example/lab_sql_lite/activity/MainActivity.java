package com.example.lab_sql_lite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
import com.example.lab_sql_lite.sql.LopDbHelper;
import com.example.lab_sql_lite.sql.SinhVienDbHelper;
import com.example.lab_sql_lite.sql.SqlLiteUtils;

public class MainActivity extends AppCompatActivity {
    private Button btCreateDb;
    private Button btDeleteDb;
    private Button btCreateTb;
    private Button btDeleteTb;

    private Button btInsertRowTbLop;

    private Button btDeleteRowTbLop;

    private Button btUpdateRowTbLop;

    private Button btQueryDataTbLop;
    private EditText edtNameTableDel;

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
        this.edtNameTableDel = findViewById(R.id.edt_name_tb_delete);

        btCreateDb.setOnClickListener(view -> clickButtonCreateDb());
        btDeleteDb.setOnClickListener(view -> clickButtonDeleteDb());
        btCreateTb.setOnClickListener(view -> clickButtonCreateTb());
        btDeleteTb.setOnClickListener(view -> clickButtonDeleteTb());
        btInsertRowTbLop.setOnClickListener(view -> clickButtonInsertRowTbLop());
        btDeleteRowTbLop.setOnClickListener(view -> clickButtonDeleteRowTbLop());
        btUpdateRowTbLop.setOnClickListener(view -> clickButtonUpdateRowTbLop());
        btQueryDataTbLop.setOnClickListener(view -> clickButtonQueryDataTbLop());
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

        if (SqlLiteUtils.checkExistDb("quan-ly-sinh-vien.db") == false) {
            Toast.makeText(this, "database quan-ly-sinh-vien chưa có trong hệ thống, hãy tạo mới ^.^", Toast.LENGTH_SHORT).show();
        } else {

            String path = "/data/data/com.example.lab_sql_lite/databases";
            String nameDb = "quan-ly-sinh-vien.db";
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
            String path = "/data/data/com.example.lab_sql_lite/databases";
            String nameDb = "quan-ly-sinh-vien.db";
            SQLiteDatabase dtb = SQLiteDatabase.openOrCreateDatabase(path + "/" + nameDb, null);
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

    public void clickButtonQueryDataTbLop(){

    }

}