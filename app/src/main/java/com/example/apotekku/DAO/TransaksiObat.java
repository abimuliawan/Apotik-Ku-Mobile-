package com.example.apotekku.DAO;

import com.orm.SugarRecord;

public class TransaksiObat extends SugarRecord {
    public Integer id_obat;
    public String nama_obat;
    public Integer jumlah_obat;

    public TransaksiObat(){}

    public TransaksiObat(Integer id_obat, String nama_obat, Integer jumlah_obat) {
        this.id_obat = id_obat;
        this.nama_obat = nama_obat;
        this.jumlah_obat = jumlah_obat;
    }

    public Integer getId_obat() {
        return id_obat;
    }

    public void setId_obat(Integer id_obat) {
        this.id_obat = id_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public Integer getJumlah_obat() {
        return jumlah_obat;
    }

    public void setJumlah_obat(Integer jumlah_obat) {
        this.jumlah_obat = jumlah_obat;
    }
}
