package com.example.lab_sql_lite.entities;

import java.io.Serializable;

public class SinhVien implements Serializable {

    private String MaSV;
    private String TenSV;
    private String MaLop;

    public SinhVien(String maSV, String tenSV, String maLop) {
        MaSV = maSV;
        TenSV = tenSV;
        MaLop = maLop;
    }

    public String getMaSV() {
        return MaSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public String getMaLop() {
        return MaLop;
    }

    @Override
    public String toString() {
        return "MaSV=" + MaSV + ", TenSV=" + TenSV + ", MaLop=" + MaLop;
    }
}
