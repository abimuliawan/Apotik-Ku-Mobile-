package com.example.apotekku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apotekku.Adapter.ObatAdapter;
import com.example.apotekku.Adapter.PilihObatAdapter;
import com.example.apotekku.DAO.ObatDAO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiObatActivity extends AppCompatActivity {

    private List<ObatDAO> obatList = new ArrayList<>();
    private ApiInterface mApiInterface;
    private RecyclerView recyclerViewObat;
    private PilihObatAdapter pilihObatAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_obat);

        recyclerViewObat = findViewById(R.id.recycler_view_transaksi_obat);

        //Inisialisasi Recycle
        pilihObatAdapter = new PilihObatAdapter(TransaksiObatActivity.this, obatList);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewObat.setLayoutManager(mLayoutManager);
        recyclerViewObat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewObat.setAdapter(pilihObatAdapter);

        setRecyclerViewObat();
    }

    private void setRecyclerViewObat() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ObatDAO>> obatCall = mApiInterface.showObat();
        obatCall.enqueue(new Callback<List<ObatDAO>>() {
            @Override
            public void onResponse(Call<List<ObatDAO>> call, Response<List<ObatDAO>> response) {
                List<ObatDAO> obatDAOList = response.body();
                pilihObatAdapter.notifyDataSetChanged();
                pilihObatAdapter = new PilihObatAdapter(TransaksiObatActivity.this, obatDAOList);
                recyclerViewObat.setAdapter(pilihObatAdapter);
            }

            @Override
            public void onFailure(Call<List<ObatDAO>> call, Throwable t) {

            }
        });
    }
}
