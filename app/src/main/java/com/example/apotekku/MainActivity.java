package com.example.apotekku;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apotekku.DAO.LoginDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewLogin;
    private Animation smallToBing,bbta,bbta2;
    private EditText txtEmail,txtPass;
    private CircularProgressButton btnLogin;
    private String username, hasil, test;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load Animation Image
        smallToBing= AnimationUtils.loadAnimation(this, R.anim.small_to_big);
        bbta= AnimationUtils.loadAnimation(this, R.anim.bbta);
        bbta2= AnimationUtils.loadAnimation(this, R.anim.bbta2);

        //Inisialisasi
        imageViewLogin=(ImageView)findViewById(R.id.imageViewLogin);
        txtEmail=(EditText)findViewById(R.id.textEmail);
        txtPass=(EditText)findViewById(R.id.textPass);
        btnLogin=(CircularProgressButton) findViewById(R.id.btnLogin);

        //Passing Animation and Start
        imageViewLogin.startAnimation(smallToBing);
        txtEmail.startAnimation(bbta2);
        txtPass.startAnimation(bbta2);
        btnLogin.startAnimation(bbta2);

        //Action Button Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Function Req Login
                requestLogin();
            }
        });
    }

    public void requestLogin(){
        if(txtEmail.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty())
        {
            Toasty.warning(MainActivity.this, "Email Password Tidak Boleh Kosong !",
                    Toast.LENGTH_SHORT, true).show();
        }
        else{
            //Button Start
            btnLogin.startAnimation();
            //Request
            mApiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<LoginDAO> userDAOCall= mApiInterface.loginRequest(txtEmail.getText().toString(),txtPass.getText().toString());
            userDAOCall.enqueue(new Callback<LoginDAO>()
            {
                public void onResponse(Call<LoginDAO> call, Response<LoginDAO> response)
                {
                    if(response.body().getResponse().equals("Succes"))
                    {
                        username = response.body().getResponse();
                        btnLogin.doneLoadingAnimation(Color.parseColor("#FFCC9B"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_done_white_48dp));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(MainActivity.this,MenuUtamaActivity.class);
                                startActivity(i);
                                finish();
                            }
                        },1000);
                    }
                    else{
                        btnLogin.revertAnimation();
                        Toasty.error(MainActivity.this, "Incorrect email and password",
                                Toast.LENGTH_SHORT, true).show();
                    }
                }

                public void onFailure(Call<LoginDAO> call, Throwable t)
                {
                    btnLogin.revertAnimation();
                    Toasty.error(MainActivity.this, t.getMessage(),
                            Toast.LENGTH_SHORT, true).show();
                }
            });
        }
    }


}
