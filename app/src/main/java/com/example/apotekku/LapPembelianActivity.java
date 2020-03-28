package com.example.apotekku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apotekku.Adapter.LaporanAdapter;
import com.example.apotekku.Adapter.TrackingAdapter;
import com.example.apotekku.DAO.LaporanDAO;
import com.example.apotekku.DAO.TrackingDAO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LapPembelianActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLap;
    private List<LaporanDAO> laporanDAOS = new ArrayList<>();
    private LaporanAdapter laporanAdapter;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_pembelian);

        recyclerViewLap = findViewById(R.id.recycler_view_pembelian);

        //Inisialisasi Recycle
        laporanAdapter = new LaporanAdapter(laporanDAOS, LapPembelianActivity.this);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewLap.setLayoutManager(mLayoutManager);
        recyclerViewLap.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLap.setAdapter(laporanAdapter);

        setRecyclerLap();
    }

    private void setRecyclerLap() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<LaporanDAO>> laporanCall = mApiInterface.showPembelian();
        laporanCall.enqueue(new Callback<List<LaporanDAO>>() {
            @Override
            public void onResponse(Call<List<LaporanDAO>> call, Response<List<LaporanDAO>> response) {
                List<LaporanDAO> laporanDAOS = response.body();
                laporanAdapter.notifyDataSetChanged();
                laporanAdapter = new LaporanAdapter(laporanDAOS, LapPembelianActivity.this);
                recyclerViewLap.setAdapter(laporanAdapter);
            }

            @Override
            public void onFailure(Call<List<LaporanDAO>> call, Throwable t) {

            }
        });
    }
}
