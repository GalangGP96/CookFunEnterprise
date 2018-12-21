package com.example.asus.cookfun.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(nama text primary key, email text null, password text null);";
        Log.d("Data", "onCreate : " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO user (no, nama, email, password) VALUES ('1', 'Nama anda', 'email anda', 'password anda');";
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
