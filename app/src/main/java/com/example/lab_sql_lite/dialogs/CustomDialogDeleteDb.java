package com.example.lab_sql_lite.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.lab_sql_lite.R;
import com.example.lab_sql_lite.sql.SqlLiteUtils;

public class CustomDialogDeleteDb extends Dialog {

    private Context context; // ngữ cảnh hiện tại
    private EditText edtNameDb;
    private Button btDelete;

    private Button btDeleteAll;
    private Button btCancel;


    public CustomDialogDeleteDb(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // loại bỏ tiêu đề của một Activity hoặc Dialog
        setContentView(R.layout.custom_dialog_delete_db);

        this.edtNameDb = findViewById(R.id.edtNameDb);
        this.btDelete = findViewById(R.id.btDelete);
        this.btDeleteAll = findViewById(R.id.btDeleteAll);
        this.btCancel = findViewById(R.id.btCancel);


        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDelete();
            }
        }); // -> gán sự kiện khi click vào button Delete

        btDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDeleteAll();
            }
        });// -> gán sự kiện khi click vào button Delete All

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonCancel();
            }
        });// -> gán sự kiện khi click vào button Cancel

    }

    public void clickButtonDelete() {
        String nameDb = this.edtNameDb.getText().toString();
        if (nameDb == null || nameDb.isEmpty()) {
            Toast.makeText(this.context, "Please enter name database !!!", Toast.LENGTH_SHORT).show();
        } else {

            if (SqlLiteUtils.checkDeleteDb(nameDb + ".db")) {
                Toast.makeText(this.context, "Xóa database thành công!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.context, "Database này không tồn tại trong hệ thống!!!", Toast.LENGTH_SHORT).show();
            }
            this.dismiss(); // close Dialog
        }
    }

    public void clickButtonDeleteAll(){

        SqlLiteUtils.deleteAllDb();
        Toast.makeText(this.context, "Tất cả database trong hệ thống đã được xóa !!!", Toast.LENGTH_SHORT).show();
        this.edtNameDb.setText("");

    }

    public void clickButtonCancel() {
        this.dismiss(); // close Dialog
    }
}
