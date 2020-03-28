package com.example.apotekku;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apotekku.Adapter.PegawaiAdapter;
import com.example.apotekku.Adapter.TrackingAdapter;
import com.example.apotekku.DAO.TrackingDAO;
import com.example.apotekku.DAO.UserDAO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTrack;
    private List<TrackingDAO> trackingDAOList = new ArrayList<>();
    private TrackingAdapter trackingAdapter;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        recyclerViewTrack = findViewById(R.id.recycler_view_tracking);

        //Inisialisasi Recycle
        trackingAdapter = new TrackingAdapter(trackingDAOList, TrackingActivity.this);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewTrack.setLayoutManager(mLayoutManager);
        recyclerViewTrack.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTrack.setAdapter(trackingAdapter);

        setRecyclerTrack();
    }

    private void setRecyclerTrack() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TrackingDAO>> trackCall = mApiInterface.showTracking();
        trackCall.enqueue(new Callback<List<TrackingDAO>>() {
            @Override
            public void onResponse(Call<List<TrackingDAO>> call, Response<List<TrackingDAO>> response) {
                List<TrackingDAO> trackingDAOS = response.body();
                trackingAdapter.notifyDataSetChanged();
                trackingAdapter = new TrackingAdapter(trackingDAOS, TrackingActivity.this);
                recyclerViewTrack.setAdapter(trackingAdapter);
            }

            @Override
            public void onFailure(Call<List<TrackingDAO>> call, Throwable t) {

            }
        });
    }
}
