package com.example.apotekku.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.R;

import java.util.List;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.MyViewHolder> {

    private List<ObatDAO> result;
    private Context context;

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
        ObatDAO obat = result.get(i);
        myViewHolder.txtNama.setText(obat.getNama_obat());
        myViewHolder.txtSaldo.setText("Saldo : Rp. "+obat.getSaldoObat());
        myViewHolder.txtStok.setText("Stok : "+obat.getStok_obat());
        myViewHolder.txtKeterangan.setText(obat.getKeterangan_obat());
        myViewHolder.txtTanggal.setText(obat.getKadaluarsa());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama, txtSaldo, txtStok, txtKeterangan, txtTanggal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_obat);
            txtSaldo = itemView.findViewById(R.id.saldo_obat);
            txtStok = itemView.findViewById(R.id.stok_obat);
            txtKeterangan = itemView.findViewById(R.id.keterangan_obat);
            txtTanggal = itemView.findViewById(R.id.tanggal_kadaluarsa);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
