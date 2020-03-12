package com.example.apotekku.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apotekku.ApiClient;
import com.example.apotekku.ApiInterface;
import com.example.apotekku.DAO.NotaObat;
import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.DAO.ResponNota;
import com.example.apotekku.DAO.TransaksiObat;
import com.example.apotekku.DataHelper;
import com.example.apotekku.R;
import com.example.apotekku.RegisterEmployeeActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihObatAdapter extends RecyclerView.Adapter<PilihObatAdapter.MyViewHolder> {

    private List<ObatDAO> result;
    private Context context;
    DataHelper dbHelper;
    TransaksiObat transaksiObat = new TransaksiObat();
    ApiInterface mApiInterface;

    public PilihObatAdapter(Context context, List<ObatDAO> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public PilihObatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_with_stok, viewGroup, false);
        final PilihObatAdapter.MyViewHolder holder = new PilihObatAdapter.MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PilihObatAdapter.MyViewHolder myViewHolder, int i) {
        final ObatDAO obat = result.get(i);
        myViewHolder.txtNama.setText(obat.getNama_obat());
        myViewHolder.txtStok.setText(obat.getStok_obat());

        myViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTransaksi(obat.getNama_obat(),obat.getId_obat(), obat.getStok_obat());
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama, txtStok;
        private CardView container;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_obat_stok);
            txtStok = itemView.findViewById(R.id.jumlah_obat_stok);
            container = itemView.findViewById(R.id.id_card_adapter_trans_stok);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public void dialogTransaksi(String nama, String id, final String stok)
    {
        final int id_obat = Integer.parseInt(id);
        final String nama_obat = nama;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = inflater.inflate(R.layout.dialog_tambah_stok,null);

        final TextView txtNama = mView.findViewById(R.id.txtNamaObat_dialog);
        final EditText editTxtJumlah = mView.findViewById(R.id.inputJumlahTransaksi);

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
                        if(Integer.parseInt(editTxtJumlah.getText().toString()) > Integer.parseInt(stok) )
                        {
                            Toasty.warning(context, "Jumlah Melampaui Batas Stok",
                                    Toast.LENGTH_SHORT, true).show();
                        }
                        else
                        {
                            int jumlah_obat = Integer.parseInt(editTxtJumlah.getText().toString());
                            transaksiObat = new TransaksiObat(id_obat, nama_obat, jumlah_obat);
                            transaksiObat.save();

                            Toasty.success(context, "Berhasil Masuk Ke Keranjang",
                                    Toast.LENGTH_SHORT, true).show();
                        }


                        NotaObat.deleteAll(NotaObat.class);
                        setPrediction();
                    }
                });

        final AlertDialog dialog = mBuilder.create();
        dialog.show();

    }

    private void setPrediction() {
        List<TransaksiObat> list;
        list = TransaksiObat.listAll(TransaksiObat.class);
        for (int i=0; i<list.size(); i++)
        {
            perIdPrediction(list.get(i).getId_obat(), list.get(i).getJumlah_obat());
        }
    }

    private void perIdPrediction(Integer id, Integer jumlah) {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponNota> responNotaCall = mApiInterface.getPrediction(id, jumlah);
        responNotaCall.enqueue(new Callback<ResponNota>() {
            @Override
            public void onResponse(Call<ResponNota> call, Response<ResponNota> response) {
                ResponNota responNota = response.body();
                List<NotaObat> responListObat = responNota.getNota_obat();
                for(int i=0; i<responListObat.size(); i++)
                {
                    NotaObat notaObat =  new NotaObat(responListObat.get(i).getId_obat(),
                            responListObat.get(i).getStok(),
                            responListObat.get(i).getHarga(), responNota.getNama_obat());
                    notaObat.save();
                }
            }

            @Override
            public void onFailure(Call<ResponNota> call, Throwable t) {

            }
        });

    }
}
