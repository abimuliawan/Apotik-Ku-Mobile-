package com.example.apotekku;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apotekku.Adapter.JumlahObatAdapter;
import com.example.apotekku.Adapter.NotaObatAdapter;
import com.example.apotekku.Adapter.ObatAdapter;
import com.example.apotekku.DAO.NotaObat;
import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.DAO.ResponNota;
import com.example.apotekku.DAO.TransaksiObat;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTransactionActivity extends AppCompatActivity {

    private List<TransaksiObat> listJumlahObat = new ArrayList<>();
    private List<NotaObat> listNotaObat = new ArrayList<>();
    private RecyclerView recyclerViewJumlahObat;
    private JumlahObatAdapter jumlahObatAdapter;

    private RecyclerView recyclerViewNotaObat;
    private NotaObatAdapter notaObatAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private LinearLayout buttonPay;
    private TextView txtTotal;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transaction);

        recyclerViewJumlahObat = findViewById(R.id.recycler_view_jumlah_obat);
        recyclerViewNotaObat = findViewById(R.id.recycler_view_price_predic);
        buttonPay = findViewById(R.id.layout_bayar);
        txtTotal = findViewById(R.id.txt_total_harga);

        setTotalHarga();

        jumlahObatAdapter = new JumlahObatAdapter(listJumlahObat, ListTransactionActivity.this);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerViewJumlahObat.setLayoutManager(mLayoutManager);
        recyclerViewJumlahObat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewJumlahObat.setAdapter(jumlahObatAdapter);
        setRecyclerViewJumlah();
        recyclerViewJumlahObat.setAdapter(jumlahObatAdapter);

        notaObatAdapter = new NotaObatAdapter(listNotaObat,ListTransactionActivity.this);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerViewNotaObat.setLayoutManager(mLayoutManager2);
        recyclerViewNotaObat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotaObat.setAdapter(notaObatAdapter);
        setRecyclerViewPrediction();
        recyclerViewNotaObat.setAdapter(notaObatAdapter);

        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentTransaction();
                Intent intent = new Intent(ListTransactionActivity.this, MenuUtamaActivity.class);
                startActivity(intent);
            }
        });
    }


    private void setRecyclerViewPrediction() {
        List<NotaObat> list;
        list = NotaObat.listAll(NotaObat.class);
        listNotaObat.addAll(list);
        notaObatAdapter.notifyDataSetChanged();
    }

    private void setRecyclerViewJumlah() {
        List<TransaksiObat> list;
        list = TransaksiObat.listAll(TransaksiObat.class);
        listJumlahObat.addAll(list);
        jumlahObatAdapter.notifyDataSetChanged();
    }

    private void setTotalHarga() {
        List<NotaObat> list;
        list = NotaObat.listAll(NotaObat.class);
        Integer total = 0;
        for (int i=0; i<list.size(); i++)
        {
            int jumlah = Integer.parseInt(list.get(i).getStok());
            int harga = Integer.parseInt(list.get(i).getHarga());
            total = total + (jumlah*harga);
        }

        String stringHarga = String.format("%,d", total);
        String ajshjas = stringHarga;
        txtTotal.setText("Rp. "+stringHarga);
    }

    private void paymentTransaction() {
        List<TransaksiObat> list;
        list = TransaksiObat.listAll(TransaksiObat.class);
        for (int i=0; i<list.size(); i++)
        {
            transactionById(list.get(i).getId_obat(), list.get(i).getJumlah_obat());
        }

        NotaObat.deleteAll(NotaObat.class);
        TransaksiObat.deleteAll(TransaksiObat.class);
        Toasty.success(ListTransactionActivity.this, "Transaksi Berhasil",
                Toast.LENGTH_SHORT, true).show();
    }

    private void transactionById(Integer id, Integer jumlah) {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponNota> responNotaCall = mApiInterface.transaction(id, jumlah);
        responNotaCall.enqueue(new Callback<ResponNota>() {
            @Override
            public void onResponse(Call<ResponNota> call, Response<ResponNota> response) {
                ResponNota responNota = response.body();
                List<NotaObat> responListObat = responNota.getNota_obat();
            }

            @Override
            public void onFailure(Call<ResponNota> call, Throwable t) {
                Toasty.error(ListTransactionActivity.this, "Terjadi Kesalahan Transaksi",
                        Toast.LENGTH_SHORT, true).show();
            }
        });
    }

}
