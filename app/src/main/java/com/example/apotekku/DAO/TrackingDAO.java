package com.example.apotekku.DAO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackingDAO {

    @SerializedName("name_user")
    @Expose
    private String name;

    @SerializedName("created_at")
    @Expose
    private String tanggal_jam;

    public TrackingDAO(String name, String tanggal_jam) {
        this.name = name;
        this.tanggal_jam = tanggal_jam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTanggal_jam() {
        return tanggal_jam;
    }

    public void setTanggal_jam(String tanggal_jam) {
        this.tanggal_jam = tanggal_jam;
    }
}
