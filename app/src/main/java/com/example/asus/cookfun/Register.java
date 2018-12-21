package com.example.asus.cookfun;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.cookfun.Model.DataHelper;
import com.example.asus.cookfun.Model.PostPutDelUser;
import com.example.asus.cookfun.Rest.*;
import com.example.asus.cookfun.Session.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    Button button_signup;

    DataHelper dbHelper;
    EditText edtNama, edtEmail, edtPassword;
    Cursor cursor;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DataHelper(this);

        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        button_signup = findViewById(R.id.button_signup);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                db.execSQL("INSERT INTO user(nama, email, password) VALUES ('"+edtNama.getText().toString()+"','"+edtEmail.getText().toString()+"','"+edtPassword.getText().toString()+"')");
                Call<PostPutDelUser> newUser = mApiInterface.postUser(
                        edtNama.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPassword.getText().toString(),
                        "Inonesia",
                        "" );
                newUser.enqueue(new Callback<PostPutDelUser>() {
                    @Override
                    public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("success")) {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            finish();
                            Intent a = new Intent(getApplicationContext(), Login.class);
                            startActivity(a);
                        }else {
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                    }
                });
//                Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();


            }
        });

    }
}
