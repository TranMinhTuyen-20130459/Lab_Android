package com.example.lab_sql_lite.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.lab_sql_lite.R;
import com.example.lab_sql_lite.sql.SqlLiteUtils;

public class CustomDialog extends android.app.Dialog {

    private int mButtonType; // cờ để phân biệt button

    String msg; // đoạn text để hiển thị thông báo cho người dùng
    private String maLop, tenLop, siSo;
    private EditText edtMALOP, edtTENLOP, edtSISO;

    private Button btCancel, btInsDelUpd;

    public CustomDialog(@NonNull Context context, int buttonType) {
        super(context);
        this.mButtonType = buttonType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mButtonType == 1) {

            setContentView(R.layout.custom_dialog_insert_row_tblop);
            this.edtMALOP = findViewById(R.id.edtMALOP);
            this.edtTENLOP = findViewById(R.id.edtTENLOP);
            this.edtSISO = findViewById(R.id.edtSISO);
            this.btCancel = findViewById(R.id.btCancel);
            this.btInsDelUpd = findViewById(R.id.btInsert);

            btCancel.setOnClickListener(view -> dismiss());
            btInsDelUpd.setOnClickListener(view -> EventDialogInsertRowTbLop());

        } else if (mButtonType == 2) {

            setContentView(R.layout.custom_dialog_delete_row_tblop);
            this.edtMALOP = findViewById(R.id.edtMALOP);
            this.btCancel = findViewById(R.id.btCancel);
            this.btInsDelUpd = findViewById(R.id.btDelete);

            btCancel.setOnClickListener(view -> dismiss());
            btInsDelUpd.setOnClickListener(view -> EventDialogDeleteRowTbLop());


        } else if (mButtonType == 3) {

            setContentView(R.layout.custom_dialog_update_row_tblop);
            this.edtMALOP = findViewById(R.id.edtMALOP);
            this.edtTENLOP = findViewById(R.id.edtTENLOP);
            this.edtSISO = findViewById(R.id.edtSISO);
            this.btCancel = findViewById(R.id.btCancel);
            this.btInsDelUpd = findViewById(R.id.btUpdate);

            btCancel.setOnClickListener(view -> dismiss());
            btInsDelUpd.setOnClickListener(view -> EventDialogUpdateRowTbLop());

        } else if (mButtonType == 4) {

        } else if (mButtonType == 5) {

        }
    }


    public void GetDataFromAllEditText() {
        maLop = edtMALOP.getText().toString();
        tenLop = edtTENLOP.getText().toString();
        siSo = edtSISO.getText().toString();
    }

    public void ResetAllEditText() {
        edtMALOP.setText("");
        edtTENLOP.setText("");
        edtSISO.setText("");
    }

    public void EventDialogInsertRowTbLop() {

        GetDataFromAllEditText();
        if (maLop.isEmpty() || tenLop.isEmpty() || siSo.isEmpty()) {
            msg = "Bạn hãy nhập đầy đủ thông tin !!!";
        } else {
            long rs = SqlLiteUtils.doInsertRecordTableLop(maLop, tenLop, siSo);
            if (rs > 0) {
                msg = "Thêm mới thành công ^.^";
                ResetAllEditText();
            } else if (rs == -2) {
                msg = "Chưa có bảng Lop trong database quan-ly-sinh-vien !!! , hãy tạo mới";
            } else {
                msg = "Mã lớp đã tồn tại trong hệ thống !!!";
            }
        }
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void EventDialogDeleteRowTbLop() {
        maLop = edtMALOP.getText().toString();
        if (maLop.isEmpty()) {
            msg = "Bạn hãy nhập đầy đủ thông tin !!!";
        } else {
            long rs = SqlLiteUtils.doDeleteRecordTable(maLop);
            if (rs > 0) {
                msg = "Đã xóa thành công ^.^";
                edtMALOP.setText("");
            } else if (rs == -2) {
                msg = "Chưa có bảng Lop trong database quan-ly-sinh-vien !!! , hãy tạo mới";
            } else {
                msg = maLop + " không có trong bảng Lop !!!";
            }
        }
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void EventDialogUpdateRowTbLop() {

        GetDataFromAllEditText();
        if (maLop.isEmpty() || tenLop.isEmpty() || siSo.isEmpty()) {
            msg = "Bạn hãy nhập đầy đủ thông tin !!!";
        } else {
            long rs = SqlLiteUtils.doUpdateTable(maLop, tenLop, siSo);
            if (rs > 0) {
                msg = "Cập nhật thành công ^.^";
                ResetAllEditText();
            } else if (rs == -2) {
                msg = "Chưa có bảng Lop trong database quan-ly-sinh-vien !!! , hãy tạo mới";
            } else {
                msg = "Cập nhật không thành công !!!";
            }
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }

    }

}
