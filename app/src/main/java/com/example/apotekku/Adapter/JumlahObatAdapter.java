package com.example.apotekku.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apotekku.DAO.TransaksiObat;
import com.example.apotekku.R;

import java.util.List;

public class JumlahObatAdapter extends RecyclerView.Adapter<JumlahObatAdapter.MyViewHolder> {

    private List<TransaksiObat> result;
    private Context context;

    public JumlahObatAdapter(List<TransaksiObat> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public JumlahObatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.saldo_adapter, viewGroup, false);
        final JumlahObatAdapter.MyViewHolder holder = new JumlahObatAdapter.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JumlahObatAdapter.MyViewHolder myViewHolder, int i) {
        TransaksiObat transaksiObat = result.get(i);
        myViewHolder.txtNama.setText(transaksiObat.getNama_obat());
        myViewHolder.txtJumlah.setText(Integer.toString(transaksiObat.getJumlah_obat()));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama, txtJumlah;
        private CardView cardEdit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_obat_jum_trans);
            txtJumlah = itemView.findViewById(R.id.jumlah_obat_jum_trans);
            cardEdit = itemView.findViewById(R.id.card_edit_trans);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
