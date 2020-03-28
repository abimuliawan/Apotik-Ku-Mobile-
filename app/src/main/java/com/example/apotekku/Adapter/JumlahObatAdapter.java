package com.example.apotekku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apotekku.DAO.NotaObat;
import com.example.apotekku.DAO.TransaksiObat;
import com.example.apotekku.ListTransactionActivity;
import com.example.apotekku.MenuUtamaActivity;
import com.example.apotekku.R;

import java.util.List;

import es.dmoral.toasty.Toasty;

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
        final TransaksiObat transaksiObat = result.get(i);
        myViewHolder.txtNama.setText(transaksiObat.getNama_obat());
        myViewHolder.txtJumlah.setText(Integer.toString(transaksiObat.getJumlah_obat()));
        myViewHolder.cardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransaksiObat hapusObat = TransaksiObat.findById(TransaksiObat.class, transaksiObat.getId());
                hapusObat.delete();
                NotaObat.deleteAll(NotaObat.class);

                Toasty.success(context, "Berhasil Menghapus dari Keranjang",
                        Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(context, MenuUtamaActivity.class);
                context.startActivity(intent);
            }
        });
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
