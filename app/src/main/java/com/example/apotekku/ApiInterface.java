package com.example.apotekku;

import com.example.apotekku.DAO.LoginDAO;
import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.DAO.ResponNota;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    //Login =============================================================================
    @POST("login")
    @FormUrlEncoded
    Call<LoginDAO> loginRequest(@Field("email") String username,
                                @Field("password") String password);

    // Laravel
    @GET("semua_obat")
    Call<List<ObatDAO>> showObat();

    @GET("prediction_price/{id_obat}/{jumlah_obat}")
    Call<ResponNota> getPrediction(@Path("id_obat") Integer id_obat,
                                   @Path("jumlah_obat") Integer jumlah_obat);

    @GET("transaction_obat/{id_obat}/{jumlah_obat}")
    Call<ResponNota> transaction(@Path("id_obat") Integer id_obat,
                                   @Path("jumlah_obat") Integer jumlah_obat);

    @POST("tambah_stok_obat")
    @FormUrlEncoded
    Call<ObatDAO> tambahStok(@Field("id_obat") String id,
                             @Field("stok") Integer jumlah,
                             @Field("harga") Integer harga,
                             @Field("tanggal") String tanggal);

    @POST("register_pegawai")
    @FormUrlEncoded
    Call<LoginDAO> registerPegawai(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("password") String password);

    //Kurang Stok ========================================================================
    @POST("KurangStok.php")
    @FormUrlEncoded
    Call<LoginDAO> kurangStok(@Field("id_obat") int idObat,
                              @Field("jumlah_tambah") int jumlahTambah);

    //Tambah Obat =======================================================================
    @POST("buat_obat")
    @FormUrlEncoded
    Call<ObatDAO> insertObat(@Field("nama_obat") String nama,
                             @Field("merk_obat") String merk,
                             @Field("keterangan") String keterangan,
                             @Field("tanggal_kadaluarsa") String kadalursa);

}
