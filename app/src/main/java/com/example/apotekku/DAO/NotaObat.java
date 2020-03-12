package com.example.apotekku.DAO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class NotaObat extends SugarRecord {
    @SerializedName("id_obat")
    @Expose
    public Integer id_obat;

    @SerializedName("stok")
    @Expose
    public String stok;

    @SerializedName("harga")
    @Expose
    public String harga;

    public String nama_obat;

    public NotaObat(){}

    public NotaObat(Integer id_obat, String stok, String harga, String nama_obat) {
        this.id_obat = id_obat;
        this.stok = stok;
        this.harga = harga;
        this.nama_obat = nama_obat;
    }

    public Integer getId_obat() {
        return id_obat;
    }

    public void setId_obat(Integer id_obat) {
        this.id_obat = id_obat;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }
}
