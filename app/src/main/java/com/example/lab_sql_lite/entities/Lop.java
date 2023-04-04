package com.example.lab_sql_lite.entities;

import java.io.Serializable;

public class Lop implements Serializable {

    private String MaLop;
    private String TenLop;
    private String SiSo;

    public Lop(String maLop, String tenLop, String siSo) {
        MaLop = maLop;
        TenLop = tenLop;
        SiSo = siSo;
    }

    public String getMaLop() {
        return MaLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public String getSiSo() {
        return SiSo;
    }

    @Override
    public String toString() {
        return "MaLop=" + MaLop +",TenLop=" + TenLop + ",SiSo=" + SiSo;
    }
}
