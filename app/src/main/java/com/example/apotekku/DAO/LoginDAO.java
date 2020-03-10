package com.example.apotekku.DAO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDAO {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("message")
    @Expose
    private String response;

    public LoginDAO(String username, String response) {
        this.username = username;
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public String getResponse() {
        return response;
    }
}
