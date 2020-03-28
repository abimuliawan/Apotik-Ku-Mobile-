package com.example.apotekku.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apotekku.DAO.TrackingDAO;
import com.example.apotekku.DAO.TransaksiObat;
import com.example.apotekku.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TrackingAdapter extends RecyclerView.Adapter<TrackingAdapter.MyViewHolder> {

    private List<TrackingDAO> result;
    private Context context;

    public TrackingAdapter(List<TrackingDAO> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public TrackingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.tracking_adapter, viewGroup, false);
        final TrackingAdapter.MyViewHolder holder = new TrackingAdapter.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrackingAdapter.MyViewHolder myViewHolder, int i) {
        TrackingDAO trackingDAO = result.get(i);

        SimpleDateFormat formatterTanggal = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat formatterJam = new SimpleDateFormat("HH:mm");

        String string = trackingDAO.getTanggal_jam();
        String[] parts = string.split(" ");
        String tanggal = parts[0];
        String jam = parts[1];

        myViewHolder.txtNama.setText(trackingDAO.getName());
        myViewHolder.txtTanggal.setText("Tanggal : "+tanggal);
        myViewHolder.txtJam.setText("Jam : "+jam);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama, txtTanggal, txtJam;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_user_track);
            txtTanggal = itemView.findViewById(R.id.tanggal_track);
            txtJam = itemView.findViewById(R.id.jam_track);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
