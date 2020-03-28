package com.example.apotekku.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apotekku.DAO.LaporanDAO;
import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.R;

import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.MyViewHolder> {

    private List<LaporanDAO> result;
    private Context context;

    public LaporanAdapter(List<LaporanDAO> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public LaporanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_transaksi, viewGroup, false);
        final LaporanAdapter.MyViewHolder holder = new LaporanAdapter.MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanAdapter.MyViewHolder myViewHolder, int i) {
        LaporanDAO laporanDAO = result.get(i);

        String string = laporanDAO.getTanggal();
        String[] parts = string.split(" ");
        String tanggal = parts[0];
        String jam = parts[1];

        Integer hargaSatuan = Integer.parseInt(laporanDAO.getHarga_satuan());
        Integer jumlah = Integer.parseInt(laporanDAO.getJumlah());
        Integer total = hargaSatuan*jumlah;
        String srtTotal = String.format("%,d", total);

        myViewHolder.txtNama.setText(laporanDAO.getNama_obat());
        myViewHolder.txtHargaSatuan.setText("Harga Satuan:"+laporanDAO.getHarga_satuan());
        myViewHolder.txtJumlahObat.setText("Jumlah :"+laporanDAO.getJumlah());
        myViewHolder.txtTotal.setText("Rp. "+srtTotal);
        myViewHolder.txtTanggal.setText(tanggal);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama, txtHargaSatuan, txtJumlahObat, txtTotal, txtTanggal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_obat_riwayat);
            txtHargaSatuan = itemView.findViewById(R.id.harga_satuan_riwayat);
            txtJumlahObat = itemView.findViewById(R.id.jumlah_obat_riwayat);
            txtTotal = itemView.findViewById(R.id.total_harga_trans);
            txtTanggal = itemView.findViewById(R.id.tanggal_riwayat);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
