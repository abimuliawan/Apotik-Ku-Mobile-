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
import com.example.apotekku.DAO.UserDAO;
import com.example.apotekku.R;

import java.util.List;

public class PegawaiAdapter extends RecyclerView.Adapter<PegawaiAdapter.MyViewHolder> {

    private List<UserDAO> result;
    private Context context;

    public PegawaiAdapter(List<UserDAO> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public PegawaiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.user_adapter, viewGroup, false);
        final PegawaiAdapter.MyViewHolder holder = new PegawaiAdapter.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PegawaiAdapter.MyViewHolder myViewHolder, int i) {
        UserDAO userDAO = result.get(i);
        myViewHolder.txtNama.setText(userDAO.getName());
        myViewHolder.txtEmail.setText(userDAO.getEmail());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama, txtEmail;
        private CardView cardEdit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.nama_user);
            txtEmail = itemView.findViewById(R.id.email);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
