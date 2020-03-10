package com.example.apotekku.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.R;

import java.util.List;

public class PilihObatAdapter extends RecyclerView.Adapter<PilihObatAdapter.MyViewHolder> {

    private List<ObatDAO> result;
    private Context context;

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
                dialogTransaksi(obat.getNama_obat());
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

    public void dialogTransaksi(String nama)
    {
        //Alert Konfirmasi
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = inflater.inflate(R.layout.dialog_tambah_stok,null);

        TextView txtNama = mView.findViewById(R.id.txtNamaObat_dialog);
        EditText editTxtJumlah = mView.findViewById(R.id.inputJumlahTransaksi);

        txtNama.setText(nama);

        mBuilder.setView(mView)
                .setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Pengambilan Data User

                    }
                })
                .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Benar
                    }
                });

        final AlertDialog dialog = mBuilder.create();
        dialog.show();

    }
}
