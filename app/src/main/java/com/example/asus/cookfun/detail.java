package com.example.asus.cookfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.cookfun.Adapter.BahanAdapter;
import com.example.asus.cookfun.Adapter.LangkahAdapter;
import com.example.asus.cookfun.Adapter.ResepAdapter;
import com.example.asus.cookfun.Model.*;
import com.example.asus.cookfun.Rest.*;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detail extends AppCompatActivity {
    private String id;
    private List<Detail> resep;
    private ApiInterface mApiInterface;
    private TextView namaResep,detailResep,namaPenulis;
    private ImageView fotoResep;
    private CircleImageView fotoPenulis;
    private RecyclerView recyclerBahan,recyclerLangkah;
    private RecyclerView.LayoutManager bahanLayoutManager,langkahLayoutManager;
    private List<Bahan> dataBahan;
    private BahanAdapter bahanAdapter;
    private List<Langkah> dataLangkah;
    private LangkahAdapter langkahAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        namaResep = (TextView)findViewById(R.id.detail_namaResep);
        detailResep = (TextView)findViewById(R.id.detail_detailResep);
        namaPenulis = (TextView)findViewById(R.id.detail_namaPenulis);
        fotoResep = (ImageView) findViewById(R.id.detail_fotoResep);
        fotoPenulis = (CircleImageView) findViewById(R.id.detail_fotoPenulis);
        recyclerBahan = (RecyclerView) findViewById(R.id.recyclerBahan);
        recyclerLangkah = (RecyclerView) findViewById(R.id.recyclerLangkah);

        id = getIntent().getStringExtra("id");
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetDetail> getDetail = mApiInterface.getResepDetail(id);
        getDetail.enqueue(new Callback<GetDetail>() {
            @Override
            public void onResponse(Call<GetDetail> call, Response<GetDetail> response) {
                resep = response.body().getDataResep();
                namaResep.setText(resep.get(0).getJudul_resep());
                detailResep.setText(resep.get(0).getDeskripsi());
                namaPenulis.setText(resep.get(0).getUsername());
                Glide.with(getApplicationContext()).load(ApiClient.BASE_RESEP+resep.get(0).getFoto_resep()).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(fotoResep);
                Glide.with(getApplicationContext()).asBitmap().load(ApiClient.BASE_USER+resep.get(0).getPhoto()).apply(RequestOptions.placeholderOf(R.drawable.logo).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(fotoPenulis);
            }

            @Override
            public void onFailure(Call<GetDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal "+t, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerBahan.setHasFixedSize(true);
        bahanLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerBahan.setLayoutManager(bahanLayoutManager);
        Call<GetBahan> getBahan = mApiInterface.getBahan(id);
        getBahan.enqueue(new Callback<GetBahan>() {
            @Override
            public void onResponse(Call<GetBahan> call, Response<GetBahan> response) {
                dataBahan = response.body().getDataBahan();
                bahanAdapter = new BahanAdapter(dataBahan,getApplicationContext());
                recyclerBahan.setAdapter(bahanAdapter);

                Log.d("Retrofit Get", "Jumlah data bahan: " +String.valueOf(dataBahan.size()));
            }

            @Override
            public void onFailure(Call<GetBahan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

        recyclerLangkah.setHasFixedSize(true);
        langkahLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerLangkah.setLayoutManager(langkahLayoutManager);
        Call<GetLangkah> getLangkah = mApiInterface.getLangkah(id);
        getLangkah.enqueue(new Callback<GetLangkah>() {
            @Override
            public void onResponse(Call<GetLangkah> call, Response<GetLangkah> response) {
                dataLangkah = response.body().getDataLangkah();
                langkahAdapter = new LangkahAdapter(dataLangkah,getApplicationContext());
                recyclerLangkah.setAdapter(langkahAdapter);

                Log.d("Retrofit Get", "Jumlah data langkah: " +String.valueOf(dataLangkah.size()));
            }

            @Override
            public void onFailure(Call<GetLangkah> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.mFavorite:
                Toast.makeText(getApplicationContext(), "You click on menu favorite", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
