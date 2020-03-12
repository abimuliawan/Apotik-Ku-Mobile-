package com.example.apotekku.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apotekku.DAO.NotaObat;
import com.example.apotekku.DAO.TransaksiObat;
import com.example.apotekku.R;

import java.util.List;

public class NotaObatAdapter extends RecyclerView.Adapter<NotaObatAdapter.MyViewHolder> {

    private List<NotaObat> result;
    private Context context;

    public NotaObatAdapter(List<NotaObat> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public NotaObatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_nota_obat, viewGroup, false);
        final NotaObatAdapter.MyViewHolder holder = new NotaObatAdapter.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotaObatAdapter.MyViewHolder myViewHolder, int i) {
        NotaObat notaObat = result.get(i);
        myViewHolder.txtNama.setText(notaObat.getNama_obat());
        myViewHolder.txtHarga.setText(notaObat.getHarga());
        myViewHolder.txtJumlah.setText(notaObat.getStok());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama, txtJumlah, txtHarga;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_obat_nota);
            txtJumlah = itemView.findViewById(R.id.jumlah_obat_nota);
            txtHarga = itemView.findViewById(R.id.harga_obat_nota);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
