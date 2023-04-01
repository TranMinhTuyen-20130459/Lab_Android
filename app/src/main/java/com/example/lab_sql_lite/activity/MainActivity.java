package com.example.lab_sql_lite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lab_sql_lite.R;
import com.example.lab_sql_lite.dialog.CustomDialogCreateDb;
import com.example.lab_sql_lite.dialog.CustomDialogDeleteDb;

public class MainActivity extends AppCompatActivity {

    private Button btCreateDb;
    private Button btDeleteDb;
    private Button btCreateTb;
    private Button btDeleteTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btCreateDb = findViewById(R.id.bt_create_db);
        this.btDeleteDb = findViewById(R.id.bt_delete_db);
        this.btCreateTb = findViewById(R.id.bt_create_tb);
        this.btDeleteTb = findViewById(R.id.bt_delete_tb);

        btCreateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonCreateDb();
            }
        });

        btDeleteDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDeleteDb();
            }
        });
        btCreateTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonCreateTb();
            }
        });
        btDeleteTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDeleteTb();
            }
        });
    }

    public void clickButtonCreateDb() {

        Dialog dialogCreateDb = new CustomDialogCreateDb(this);
        dialogCreateDb.show();
    }

    public void clickButtonDeleteDb() {
        Dialog dialogDeleteDb = new CustomDialogDeleteDb(this);
        dialogDeleteDb.show();
    }

    public void clickButtonCreateTb() {

    }

    public void clickButtonDeleteTb() {

    }
}