package com.example.lab_sql_lite.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_sql_lite.R;
import com.example.lab_sql_lite.entities.Lop;

import java.util.List;

public class DataTableLop extends AppCompatActivity {

    private ListView listView;

    private List<Lop> arrayLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_table_lop);

        this.listView = findViewById(R.id.lvLop);

        arrayLop = (List<Lop>) getIntent().getSerializableExtra("listLop");;

        ArrayAdapter adapter = new ArrayAdapter(this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                arrayLop);

        listView.setAdapter(adapter);
    }
}