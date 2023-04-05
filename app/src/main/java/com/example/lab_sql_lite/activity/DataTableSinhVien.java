package com.example.lab_sql_lite.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_sql_lite.R;
import com.example.lab_sql_lite.entities.Lop;
import com.example.lab_sql_lite.entities.SinhVien;

import java.util.List;

public class DataTableSinhVien extends AppCompatActivity {

    private ListView listView;

    private List<SinhVien> arraySV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_table_sinhvien);

        this.listView = findViewById(R.id.lvSinhVien);

        arraySV = (List<SinhVien>) getIntent().getSerializableExtra("listSinhVien");;

        ArrayAdapter adapter = new ArrayAdapter(this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                arraySV);

        listView.setAdapter(adapter);
    }
}