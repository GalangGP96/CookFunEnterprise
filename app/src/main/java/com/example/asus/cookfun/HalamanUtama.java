package com.example.asus.cookfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.cookfun.Session.SessionManagement;

public class HalamanUtama extends AppCompatActivity {

    Button btnMasuk;
    TextView txtDaftar;
    SessionManagement mSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        mSesion = new SessionManagement(getApplicationContext());
        btnMasuk = (Button) findViewById(R.id.btnMasuk);
        txtDaftar = (TextView) findViewById(R.id.txtDaftar);

        if (mSesion.isLoggedIn()){
            Intent i = new Intent(getApplicationContext(),Home.class);
            startActivity(i);
            finish();
        }

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
    }
}
