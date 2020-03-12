package com.example.apotekku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.example.apotekku.Adapter.ObatAdapter;
import com.example.apotekku.DAO.NotaObat;
import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.DAO.ResponNota;
import com.example.apotekku.DAO.TransaksiObat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowObatActivity extends AppCompatActivity {

    private List<ObatDAO> obatList = new ArrayList<>();
    private ApiInterface mApiInterface;
    private RecyclerView recyclerViewObat;
    private ObatAdapter obatAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_obat);

        recyclerViewObat = findViewById(R.id.recycler_view_daftar_obat);

        //Inisialisasi Recycle
        obatAdapter = new ObatAdapter(ShowObatActivity.this, obatList);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewObat.setLayoutManager(mLayoutManager);
        recyclerViewObat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewObat.setAdapter(obatAdapter);

        setRecyclerViewObat();
    }

    private void setRecyclerViewObat() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ObatDAO>> obatCall = mApiInterface.showObat();
        obatCall.enqueue(new Callback<List<ObatDAO>>() {
            @Override
            public void onResponse(Call<List<ObatDAO>> call, Response<List<ObatDAO>> response) {
                List<ObatDAO> obatDAOList = response.body();
                obatAdapter.notifyDataSetChanged();
                obatAdapter = new ObatAdapter(ShowObatActivity.this, obatDAOList);
                recyclerViewObat.setAdapter(obatAdapter);
            }

            @Override
            public void onFailure(Call<List<ObatDAO>> call, Throwable t) {

            }
        });
    }


}
