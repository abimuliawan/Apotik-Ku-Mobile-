package com.example.apotekku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apotekku.DAO.LoginDAO;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterEmployeeActivity extends AppCompatActivity {

    private EditText namaPegawai, emailPegawai, passwordPegawai;
    private Button btnPegawai;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        namaPegawai = findViewById(R.id.txtNamaPegawai);
        emailPegawai = findViewById(R.id.txtEmailObat);
        passwordPegawai = findViewById(R.id.txtPasswordObat);
        btnPegawai = findViewById(R.id.btnInsertPegawai);

        btnPegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPegawai();
            }

        });

    }

    private void insertPegawai() {
        if(namaPegawai.getText().toString().isEmpty() || emailPegawai.getText().toString().isEmpty() ||
        passwordPegawai.getText().toString().isEmpty())
        {
            Toasty.warning(RegisterEmployeeActivity.this, "Form Tidak Boleh Kosong",
                    Toast.LENGTH_SHORT, true).show();
        }
        else {
            mApiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<LoginDAO> userDAOCall= mApiInterface.registerPegawai(namaPegawai.getText().toString(),emailPegawai.getText().toString(),
                    passwordPegawai.getText().toString());
            userDAOCall.enqueue(new Callback<LoginDAO>() {
                @Override
                public void onResponse(Call<LoginDAO> call, Response<LoginDAO> response) {
                    Toasty.success(RegisterEmployeeActivity.this, "Berhasil Tambah Pegawai",
                            Toast.LENGTH_SHORT, true).show();

                    Intent intent = new Intent(RegisterEmployeeActivity.this, MenuUtamaActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<LoginDAO> call, Throwable t) {
                    Toasty.error(RegisterEmployeeActivity.this, t.getMessage(),
                            Toast.LENGTH_SHORT, true).show();
                }
            });
        }
    }

}
