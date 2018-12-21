package com.example.asus.cookfun.Rest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

import com.example.asus.cookfun.Model.*;

public interface ApiInterface {
    //  Login
    @FormUrlEncoded
    @POST("Login")
    Call<PostPutDelUser> getLogin(@Field("email") String email,
                                  @Field("password") String password);

    //  User
    @GET("User/{id}")
    Call<GetUser> getUserBy(@Path("id") String id);

    @FormUrlEncoded
    @POST("User")
    Call<PostPutDelUser> postUser(@Field("username") String username,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("country") String country,
                                      @Field("description") String description);

    //  Resep
    @GET("ResepMain")
    Call<GetResep> getResep();

    //  Detail
    @FormUrlEncoded
    @POST("ResepDetail")
    Call<GetDetail> getDetail(@Field("id") String id);

    @GET("ResepDetail/{id}")
    Call<GetDetail> getResepDetail(@Path("id") String id);

    @GET("Bahan/{id}")
    Call<GetBahan> getBahan(@Path("id") String id);

    @GET("Langkah/{id}")
    Call<GetLangkah> getLangkah(@Path("id") String id);

    //  Favorite
    @GET("Favorite/{id}")
    Call<GetResep> getFavorite(@Path("id") String id);
}
