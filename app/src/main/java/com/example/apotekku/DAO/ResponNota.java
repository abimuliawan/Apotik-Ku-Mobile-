package com.example.apotekku.DAO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponNota  {

    @SerializedName("nota")
    @Expose
    public List<NotaObat> nota_obat;

    @SerializedName("nama_obat")
    @Expose
    public String nama_obat;

    public ResponNota(List<NotaObat> nota_obat, String nama_obat) {
        this.nota_obat = nota_obat;
        this.nama_obat = nama_obat;
    }

    public List<NotaObat> getNota_obat() {
        return nota_obat;
    }

    public void setNota_obat(List<NotaObat> nota_obat) {
        this.nota_obat = nota_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }
}
