package com.example.apotekku.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.apotekku.ApiClient;
import com.example.apotekku.ApiInterface;
import com.example.apotekku.DAO.NotaObat;
import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.DAO.TransaksiObat;
import com.example.apotekku.MenuUtamaActivity;
import com.example.apotekku.R;
import com.example.apotekku.TambahObatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.MyViewHolder> {

    private List<ObatDAO> result;
    private Context context;
    private ApiInterface mApiInterface;

    public ObatAdapter(Context context, List<ObatDAO> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public ObatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.obat_adapter, viewGroup, false);
        final ObatAdapter.MyViewHolder holder = new ObatAdapter.MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ObatAdapter.MyViewHolder myViewHolder, int i) {
        final ObatDAO obat = result.get(i);
        myViewHolder.txtNama.setText(obat.getNama_obat());
        myViewHolder.txtSaldo.setText("Saldo : Rp. "+obat.getSaldoObat());
        myViewHolder.txtStok.setText("Stok : "+obat.getStok_obat());
        myViewHolder.txtKeterangan.setText(obat.getKeterangan_obat());
        myViewHolder.txtTanggal.setText(obat.getKadaluarsa());

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddObat(obat.getNama_obat(), obat.getId_obat());
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama, txtSaldo, txtStok, txtKeterangan, txtTanggal;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_obat);
            txtSaldo = itemView.findViewById(R.id.saldo_obat);
            txtStok = itemView.findViewById(R.id.stok_obat);
            txtKeterangan = itemView.findViewById(R.id.keterangan_obat);
            txtTanggal = itemView.findViewById(R.id.tanggal_kadaluarsa);
            cardView = itemView.findViewById(R.id.cardViewAdapterObat);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public void dialogAddObat(String nama, final String id)
    {
        final int id_obat = Integer.parseInt(id);
        final String nama_obat = nama;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = inflater.inflate(R.layout.dialog_add_obat,null);

        final TextView txtNama = mView.findViewById(R.id.txtNamaObat_dialog_2);
        final EditText editTxtJumlah = mView.findViewById(R.id.inputJumlahObat);
        final EditText editTxtHarga = mView.findViewById(R.id.inputHargaJualObat);

        txtNama.setText(nama);
        mBuilder.setView(mView)
                .setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Benar
                        Integer jumlah = Integer.parseInt(editTxtJumlah.getText().toString());
                        Integer harga = Integer.parseInt(editTxtHarga.getText().toString());
                        tambahStokObat(id,jumlah, harga);
                    }
                });

        final AlertDialog dialog = mBuilder.create();
        dialog.show();

    }

    public void tambahStokObat(String id, Integer jumlah, Integer harga)
    {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ObatDAO> daoCall = mApiInterface.tambahStok(id, jumlah, harga, formattedDate);
        daoCall.enqueue(new Callback<ObatDAO>() {
            @Override
            public void onResponse(Call<ObatDAO> call, Response<ObatDAO> response) {
                Toasty.success(context, "Berhasil Menambah Stok",
                        Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(context, MenuUtamaActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ObatDAO> call, Throwable t) {

            }
        });

    }
}
