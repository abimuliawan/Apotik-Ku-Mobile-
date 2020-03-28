package com.example.apotekku.DAO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanDAO {

    @SerializedName("id_obat")
    @Expose
    private String id_obat;

    @SerializedName("nama_obat")
    @Expose
    private String nama_obat;

    @SerializedName("jumlah")
    @Expose
    private String jumlah;

    @SerializedName("harga_satuan")
    @Expose
    private String harga_satuan;

    @SerializedName("created_at")
    @Expose
    private String tanggal;

    public LaporanDAO(String id_obat, String nama_obat, String jumlah, String harga_satuan, String tanggal) {
        this.id_obat = id_obat;
        this.nama_obat = nama_obat;
        this.jumlah = jumlah;
        this.harga_satuan = harga_satuan;
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId_obat() {
        return id_obat;
    }

    public void setId_obat(String id_obat) {
        this.id_obat = id_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(String harga_satuan) {
        this.harga_satuan = harga_satuan;
    }
}
