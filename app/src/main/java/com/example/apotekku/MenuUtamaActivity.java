package com.example.apotekku;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apotekku.DAO.LoginDAO;
import com.example.apotekku.DAO.ObatDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MenuUtamaActivity extends AppCompatActivity {

    private LinearLayout btnDataBarang, btnPengadaanBarang,
            btnPenjualanBarang, btnLogOut, btnLaporan, btnTambahBarang;

    private CardView cardViewPenjualan, cardViewPembelian;
    private EditText txtJumlahStok;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        //Inisialisasi LinearLayout Menjadi Button
        btnDataBarang = findViewById(R.id.btnDataBarang);
        btnPengadaanBarang = findViewById(R.id.btnPengadaanBarang);
        btnPenjualanBarang = findViewById(R.id.btnPenjualanBarang);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnLaporan = findViewById(R.id.btnLaporan);
        btnTambahBarang = findViewById(R.id.btnTambahBarang);

        // Click btnDataBarang
        btnDataBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtamaActivity.this,ShowObatActivity.class);
                startActivity(intent);
            }
        });

        // Click btnPengadaanBarang
        btnPengadaanBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtamaActivity.this, TambahStokObatActivity.class);
                startActivity(intent);
            }
        });

        // Click btnPenjualanBarang
        btnPenjualanBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MenuUtamaActivity.this, TransaksiObatActivity.class);
               startActivity(intent);
            }
        });

        // Click btnLogOut
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Click btnLaporan
        btnLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //New Alert Option Laporan
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuUtamaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_laporan,null);

                //Set Card View
                cardViewPembelian = mView.findViewById(R.id.cardViewLaporanPembelian);
                cardViewPenjualan = mView.findViewById(R.id.cardViewLaporanPenjualan);

                mBuilder.setView(mView)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Cancel

                            }
                        });

                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        // Click btnTambahBarang
        btnTambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtamaActivity.this, TambahObatActivity.class);
                startActivity(intent);

            }
        });
    }



    private void tambahStokObat(int jumlah_tambah, int id_obat)
    {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<LoginDAO> userDAOCall= mApiInterface.tambahStok(id_obat,jumlah_tambah);

        userDAOCall.enqueue(new Callback<LoginDAO>()
        {
            public void onResponse(Call<LoginDAO> call, Response<LoginDAO> response)
            {
                    Toasty.success(MenuUtamaActivity.this, "Obat berhasil ditambah",
                            Toast.LENGTH_SHORT, true).show();
            }

            public void onFailure(Call<LoginDAO> call, Throwable t)
            {
                Toasty.error(MenuUtamaActivity.this, t.getMessage(),
                        Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    private void kurangStokObat(int jumlah_kurang, int id_obat)
    {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<LoginDAO> userDAOCall= mApiInterface.kurangStok(id_obat,jumlah_kurang);
        userDAOCall.enqueue(new Callback<LoginDAO>()
        {
            public void onResponse(Call<LoginDAO> call, Response<LoginDAO> response)
            {
                Toasty.success(MenuUtamaActivity.this, "Obat berhasil dikurang",
                        Toast.LENGTH_SHORT, true).show();
            }

            public void onFailure(Call<LoginDAO> call, Throwable t)
            {
                Toasty.error(MenuUtamaActivity.this, t.getMessage(),
                        Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}
