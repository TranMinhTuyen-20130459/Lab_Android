package com.example.lab_sql_lite.dialog;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.lab_sql_lite.R;
import com.example.lab_sql_lite.sql.SqlLiteUtils;

public class CustomDialogCreateDb extends Dialog {

    private Context context; // ngữ cảnh hiện tại
    private EditText edtNameDb;
    private Button btOk;
    private Button btCancel;


    public CustomDialogCreateDb(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // loại bỏ tiêu đề của một Activity hoặc Dialog
        setContentView(R.layout.custom_dialog_create_db);

        this.edtNameDb = findViewById(R.id.edtNameDb);
        this.btOk = findViewById(R.id.btOK);
        this.btCancel = findViewById(R.id.btCancel);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonOk();
            }
        }); // -> gán sự kiện khi click vào button OK

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonCancel();
            }
        });// -> gán sự kiện khi click vào button Cancel

    }

    public void clickButtonOk() {
        String nameDb = this.edtNameDb.getText().toString();
        if (nameDb == null || nameDb.isEmpty()) {
            Toast.makeText(this.context, "Please enter name database !!!", Toast.LENGTH_SHORT).show();
            /*
             - context: Application context
             - message: Nội dung thông điệp sẽ được hiển thị
             - duration: Chấp nhận một trong hai giá trị Toast.LENGTH_LONG ( 1 ) hoặc Toast.LENGTH_SHORT ( 0 ),
                 + duration = Toast.LENGTH_LONG, có nghĩa là Toast sẽ hiển thị trong một khoảng thời gian dài, cụ thể là 3.5 giây.
                 + duration = Toast.LENGTH_SHORT, có nghĩa là Toast sẽ hiển thị trong một khoảng thời gian ngắn, cụ thể là 2 giây.
             */
        } else {

            boolean checkExistDb = SqlLiteUtils.checkExistDb(nameDb+".db");
            if (checkExistDb) {
                Toast.makeText(this.context, "Database này đã tồn tại trong hệ thống !!!", Toast.LENGTH_SHORT).show();
            } else {

                SqlLiteUtils.createDb(nameDb+".db");
                Toast.makeText(this.context, "Tạo database thành công !!!", Toast.LENGTH_SHORT).show();

            }

            this.dismiss(); // close Dialog
        }
    }

    public void clickButtonCancel() {
        this.dismiss(); // close Dialog
    }
}
