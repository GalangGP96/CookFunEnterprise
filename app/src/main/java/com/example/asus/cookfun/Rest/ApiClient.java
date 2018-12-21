package com.example.asus.cookfun.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://192.168.1.45/cookfun_web_service/index.php/";
    public static final String BASE_USER = "http://192.168.1.45/cookfun_web_service/images/user/";
    public static final String BASE_RESEP = "http://192.168.1.45/cookfun_web_service/images/resep/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

