package com.example.apotekku;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.apotekku.Adapter.ObatAdapter;
import com.example.apotekku.Adapter.PegawaiAdapter;
import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.DAO.UserDAO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PegawaiActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private List<UserDAO> userDAOList = new ArrayList<>();
    private PegawaiAdapter pegawaiAdapter;
    private ApiInterface mApiInterface;
    private FloatingActionButton buttonAdd, buttonTracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegawai);

        recyclerViewUser = findViewById(R.id.recycler_view_user);

        //Inisialisasi Recycle
        pegawaiAdapter = new PegawaiAdapter(userDAOList, PegawaiActivity.this);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewUser.setLayoutManager(mLayoutManager);
        recyclerViewUser.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUser.setAdapter(pegawaiAdapter);


        buttonTracking = findViewById(R.id.fab_to_tracking);
        buttonAdd = findViewById(R.id.fab_add_pegawai);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PegawaiActivity.this, RegisterEmployeeActivity.class);
                startActivity(intent);
            }
        });

        buttonTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PegawaiActivity.this, TrackingActivity.class);
                startActivity(intent);
            }
        });

        setRecyclerUser();
    }

    private void setRecyclerUser() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<UserDAO>> userCall = mApiInterface.showPegawai();
        userCall.enqueue(new Callback<List<UserDAO>>() {
            @Override
            public void onResponse(Call<List<UserDAO>> call, Response<List<UserDAO>> response) {
                List<UserDAO> userDAOS = response.body();
                pegawaiAdapter.notifyDataSetChanged();
                pegawaiAdapter = new PegawaiAdapter(userDAOS, PegawaiActivity.this);
                recyclerViewUser.setAdapter(pegawaiAdapter);
            }

            @Override
            public void onFailure(Call<List<UserDAO>> call, Throwable t) {

            }
        });
    }
}
