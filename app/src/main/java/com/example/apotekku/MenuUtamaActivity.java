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

    private CardView cardViewPenjualan, cardViewPembelian, cardViewStokPerObat;
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
                Intent intent = new Intent(MenuUtamaActivity.this, PegawaiActivity.class);
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
                Intent intent = new Intent(MenuUtamaActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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
                cardViewStokPerObat = mView.findViewById(R.id.cardViewLaporanStokObat);

                mBuilder.setView(mView)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Cancel

                            }
                        });

                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                cardViewPembelian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuUtamaActivity.this, LapPembelianActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                cardViewPenjualan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuUtamaActivity.this, LapPenjualanActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                cardViewStokPerObat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuUtamaActivity.this, SaldoPerObatActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
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

}
