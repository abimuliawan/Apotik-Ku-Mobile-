package com.example.apotekku;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apotekku.DAO.ObatDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TambahObatActivity extends AppCompatActivity {

    private EditText txtKadaluarsa,txtNama,txtMerk,txtKeterangan;
    private Button btnInsertObat;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    final Calendar myCalendar = Calendar.getInstance();
    private ProgressDialog dialog;
    private LinearLayout linearLayout;
    private ApiInterface mApiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_obat);

        //Set Edit Text
        linearLayout = (LinearLayout) findViewById(R.id.root_layout);
        txtNama = (EditText) findViewById(R.id.txtNamaObat);
        txtMerk = (EditText) findViewById(R.id.txtMerkObat);
        txtKeterangan = (EditText) findViewById(R.id.txtKeteranganObat);
        btnInsertObat = (Button) findViewById(R.id.btnInsertObat);

        //Set Date picker
        txtKadaluarsa = (EditText) findViewById(R.id.txtKadaluarsaObat);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        txtKadaluarsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TambahObatActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Insert Obat
        btnInsertObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertObat();
            }
        });


    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtKadaluarsa.setText(sdf.format(myCalendar.getTime()));
    }


    public void insertObat(){
        dialog = new ProgressDialog(TambahObatActivity.this);
        dialog.setTitle("Please Wait");
        dialog.setMessage("Tambah Obat...");
        dialog.show();

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ObatDAO> userDAOCall = mApiInterface.insertObat(txtNama.getText().toString(),
                                                            txtMerk.getText().toString(),
                                                            txtKeterangan.getText().toString(),
                                                            txtKadaluarsa.getText().toString());

        userDAOCall.enqueue(new Callback<ObatDAO>() {
            @Override
            public void onResponse(Call<ObatDAO> call, Response<ObatDAO> response) {
                if(response.isSuccessful())
                {
                    dialog.dismiss();
                    SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                    snackbarText.append("Data Berhasil Ditambah ! : ");
                    int boldStart = snackbarText.length();
                    snackbarText.append(txtNama.getText().toString());
                    snackbarText.setSpan(new ForegroundColorSpan(Color.GREEN), boldStart, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), boldStart, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.append(".");

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, snackbarText, Snackbar.LENGTH_LONG)
                            .setDuration(1000);
                    snackbar.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(TambahObatActivity.this,MenuUtamaActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },1000);
                }
                else{
                    dialog.dismiss();
                    Toasty.error(TambahObatActivity.this, "Gagal Menambah Data",
                            Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<ObatDAO> call, Throwable t) {
                dialog.dismiss();
                Toasty.error(TambahObatActivity.this, t.getMessage(),
                        Toast.LENGTH_SHORT, true).show();
            }
        });

    }

}
