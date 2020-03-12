package com.example.apotekku;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PegawaiActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private FloatingActionButton buttonAdd, buttonTracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegawai);

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
    }
}
