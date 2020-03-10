package com.example.apotekku.DAO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObatDAO {

    @SerializedName("id_obat")
    @Expose
    public String id_obat;

    @SerializedName("nama_obat")
    @Expose
    public String nama_obat;

    @SerializedName("merk_obat")
    @Expose
    public String merk_obat;

    @SerializedName("keterangan")
    @Expose
    public String keterangan_obat;

    @SerializedName("stok_obat")
    @Expose
    public String stok_obat;

    @SerializedName("image")
    @Expose
    public String gambar_obat;

    @SerializedName("tanggal_kadaluarsa")
    @Expose
    public String kadaluarsa;

    public ObatDAO(String id_obat, String nama_obat, String merk_obat, String keterangan_obat, String stok_obat, String gambar_obat, String kadaluarsa) {
        this.id_obat = id_obat;
        this.nama_obat = nama_obat;
        this.merk_obat = merk_obat;
        this.keterangan_obat = keterangan_obat;
        this.stok_obat = stok_obat;
        this.gambar_obat = gambar_obat;
        this.kadaluarsa = kadaluarsa;
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

    public String getMerk_obat() {
        return merk_obat;
    }

    public void setMerk_obat(String merk_obat) {
        this.merk_obat = merk_obat;
    }

    public String getKeterangan_obat() {
        return keterangan_obat;
    }

    public void setKeterangan_obat(String keterangan_obat) {
        this.keterangan_obat = keterangan_obat;
    }

    public String getStok_obat() {
        return stok_obat;
    }

    public void setStok_obat(String stok_obat) {
        this.stok_obat = stok_obat;
    }

    public String getGambar_obat() {
        return gambar_obat;
    }

    public void setGambar_obat(String gambar_obat) {
        this.gambar_obat = gambar_obat;
    }

    public String getKadaluarsa() {
        return kadaluarsa;
    }

    public void setKadaluarsa(String kadaluarsa) {
        this.kadaluarsa = kadaluarsa;
    }
}
