package com.example.apotekku;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.apotekku.Adapter.LaporanAdapter;
import com.example.apotekku.Adapter.ObatAdapter;
import com.example.apotekku.Adapter.SaldoPerObatAdapter;
import com.example.apotekku.DAO.LaporanDAO;
import com.example.apotekku.DAO.ObatDAO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaldoPerObatActivity extends AppCompatActivity {

    private List<ObatDAO> obatList = new ArrayList<>();
    private ApiInterface mApiInterface;
    private RecyclerView recyclerViewSaldoPerObat;
    private SaldoPerObatAdapter saldoPerObatAdapter;
    SaldoPerObatAdapter.RecyclerViewClickListener listener;
    private String id_obat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo_per_obat);

        recyclerViewSaldoPerObat = findViewById(R.id.recycler_view_daftar_saldo_per_obat);

        //Inisialisasi Recycle
        saldoPerObatAdapter = new SaldoPerObatAdapter(SaldoPerObatActivity.this, obatList, listener);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewSaldoPerObat.setLayoutManager(mLayoutManager);
        recyclerViewSaldoPerObat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSaldoPerObat.setAdapter(saldoPerObatAdapter);

        registerForContextMenu(recyclerViewSaldoPerObat);

        setRecyclerViewObat();

        listener = new SaldoPerObatAdapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Toast.makeText(SaldoPerObatActivity.this, "Mengunduh Laporan Stok...", Toast.LENGTH_SHORT).show();
                //downloadReport(id_obat);
            }
        };
    }

    private void setRecyclerViewObat() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ObatDAO>> obatCall = mApiInterface.showObat();
        obatCall.enqueue(new Callback<List<ObatDAO>>() {
            @Override
            public void onResponse(Call<List<ObatDAO>> call, Response<List<ObatDAO>> response) {
                List<ObatDAO> obatDAOList = response.body();
                saldoPerObatAdapter.notifyDataSetChanged();
                saldoPerObatAdapter = new SaldoPerObatAdapter(SaldoPerObatActivity.this, obatDAOList, listener);
                recyclerViewSaldoPerObat.setAdapter(saldoPerObatAdapter);
            }

            @Override
            public void onFailure(Call<List<ObatDAO>> call, Throwable t) {

            }
        });
    }

    public void downloadReport (String id_obat) {
        DownloadManager downloadManager = (DownloadManager) SaldoPerObatActivity.this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("http://muliawan-labs.xyz/api/report_saldo/"+id_obat);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = downloadManager.enqueue(request);

        Toast.makeText(this, "Mengunduh Laporan Stok...", Toast.LENGTH_SHORT).show();
    }
}
