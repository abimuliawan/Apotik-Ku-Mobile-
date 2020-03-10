package com.example.apotekku;

import com.example.apotekku.DAO.LoginDAO;
import com.example.apotekku.DAO.ObatDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    //Login =============================================================================
    @POST("login")
    @FormUrlEncoded
    Call<LoginDAO> loginRequest(@Field("email") String username,
                                @Field("password") String password);

    //Tambah Stok ========================================================================
    @POST("TambahStok.php")
    @FormUrlEncoded
    Call<LoginDAO> tambahStok(@Field("id_obat") int idObat,
                              @Field("jumlah_tambah") int jumlahTambah);

    //Kurang Stok ========================================================================
    @POST("KurangStok.php")
    @FormUrlEncoded
    Call<LoginDAO> kurangStok(@Field("id_obat") int idObat,
                              @Field("jumlah_tambah") int jumlahTambah);

    //Show Obat ==========================================================================
    @GET("ShowObat.php")
    Call<List<ObatDAO>> showObat();

    //Tambah Obat =======================================================================
    @POST("InsertObat.php")
    @FormUrlEncoded
    Call<ObatDAO> insertObat(@Field("nama_obat") String nama,
                             @Field("merk_obat") String merk,
                             @Field("keterangan") String keterangan,
                             @Field("tanggal_kadaluarsa") String kadalursa);

}
