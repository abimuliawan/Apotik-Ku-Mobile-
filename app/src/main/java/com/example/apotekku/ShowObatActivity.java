package com.example.apotekku;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private List<ObatDAO> obats;
    private static int itemIndex;

    private static final String ID_OBAT = "id_obat";
    private static final String NAMA_OBAT = "nama_obat";
    private static final String MERK_OBAT = "merk_obat";
    private static final String KETERANGAN = "keterangan_obat";
    private static final String TANGGAL_KADALUARSA = "kadaluarsa";

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

        registerForContextMenu(recyclerViewObat);

        setRecyclerViewObat();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_obat, menu);

        getSupportActionBar().setTitle("Data Obat");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Obat...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                obatAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                obatAdapter.getFilter().filter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       /*AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
       int position = 1;
       final ObatDAO obt = obatList.get(position);*/
       switch (item.getItemId())
       {
           case 1:
                openEditObatActivity();
              /* Intent editIntent = new Intent(ShowObatActivity.this, EditObatActivity.class);
               editIntent.putExtra(ID_OBAT, obt.getId_obat());
               editIntent.putExtra(NAMA_OBAT, obt.getNama_obat());
               editIntent.putExtra(MERK_OBAT, obt.getMerk_obat());
               editIntent.putExtra(KETERANGAN, obt.getKeterangan_obat());
               editIntent.putExtra(TANGGAL_KADALUARSA, obt.getKadaluarsa());
               startActivity(editIntent);*/
               return true;

           case 2:
               Toast.makeText(this, "This is my Toast message!", Toast.LENGTH_LONG).show();
               return true;

           default:
               return super.onContextItemSelected(item);

       }

   }

    private void openEditObatActivity() {
        Intent editIntent = new Intent(ShowObatActivity.this, EditObatActivity.class);
        startActivity(editIntent);
    }

}
