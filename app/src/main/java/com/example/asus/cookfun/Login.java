package com.example.asus.cookfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.cookfun.Session.SessionManagement;
import com.example.asus.cookfun.Model.User;
import com.example.asus.cookfun.Model.PostPutDelUser;
import com.example.asus.cookfun.Rest.ApiClient;
import com.example.asus.cookfun.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button buttonLogin;
    EditText edtEmail, edtPassword;
    ApiInterface mApiInterface;
    SessionManagement mSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        buttonLogin = findViewById(R.id.btnLogin);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mSesion = new SessionManagement(getApplicationContext());

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelUser> getlogin = mApiInterface.getLogin( edtEmail.getText().toString(), edtPassword.getText().toString());
                getlogin.enqueue(new Callback<PostPutDelUser>() {
                    @Override
                    public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                        String status = response.body().getStatus();
                        if (status.equals("okee")){
                            User usr = response.body().getmUser();
                            mSesion.createLoginSession(usr.getId(),usr.getUsername());

                            Intent i = new Intent(getApplicationContext(),Home.class);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"Email atau password salah",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
