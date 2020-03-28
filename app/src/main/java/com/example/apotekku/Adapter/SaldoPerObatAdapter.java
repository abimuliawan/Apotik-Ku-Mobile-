package com.example.apotekku.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apotekku.ApiInterface;
import com.example.apotekku.DAO.ObatDAO;
import com.example.apotekku.R;
import com.example.apotekku.SaldoPerObatActivity;

import java.util.List;

public class SaldoPerObatAdapter extends RecyclerView.Adapter<SaldoPerObatAdapter.MyViewHolder> {
    public List<ObatDAO> result;
    private Context context;
    private RecyclerViewClickListener mListener;
    private DownloadManager downloadManager;

    public SaldoPerObatAdapter(Context context, List<ObatDAO> result, RecyclerViewClickListener listener) {
        this.context = context;
        this.result = result;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SaldoPerObatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_saldo_per_obat, viewGroup, false);
        final SaldoPerObatAdapter.MyViewHolder holder = new SaldoPerObatAdapter.MyViewHolder(v, mListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaldoPerObatAdapter.MyViewHolder myViewHolder, int i) {

        final ObatDAO obat = result.get(i);
        myViewHolder.txtNama.setText(obat.getNama_obat());

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadRepot(obat.getId_obat());
            }
        });
    }

    public void downloadRepot(String id_detail_barang)
    {
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("http://muliawan-labs.xyz/api/report_saldo/"+id_detail_barang);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = downloadManager.enqueue(request);

        Toast.makeText(context,"Download Laporan...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama;
        private CardView cardView;
        private RecyclerViewClickListener mListener;
        private LinearLayout mRowContainer;

        public MyViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_obat);
            cardView = itemView.findViewById(R.id.cardViewAdapterObat);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }


    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }
}
