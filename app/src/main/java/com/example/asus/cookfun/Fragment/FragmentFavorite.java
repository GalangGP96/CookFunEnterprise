package com.example.asus.cookfun.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.cookfun.Adapter.FavoriteAdapter;
import com.example.asus.cookfun.Adapter.ResepAdapter;
import com.example.asus.cookfun.Listener.ClickListener;
import com.example.asus.cookfun.Listener.RecyclerTouchListener;
import com.example.asus.cookfun.Model.GetResep;
import com.example.asus.cookfun.Model.Resep;
import com.example.asus.cookfun.R;
import com.example.asus.cookfun.Rest.ApiClient;
import com.example.asus.cookfun.Rest.ApiInterface;
import com.example.asus.cookfun.Session.SessionManagement;
import com.example.asus.cookfun.detail;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFavorite extends Fragment {
    private RecyclerView rv;
    private FavoriteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Resep> dataset;
    private ApiInterface mApiInterface;
    SessionManagement sessionManagement;
    String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        rv = (RecyclerView) view.findViewById(R.id.recyclerFavorite);
        sessionManagement = new SessionManagement(getContext());
        HashMap<String, String> user=sessionManagement.getUserInformation();
        id = user.get("id");
        rv.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetResep> getResep = mApiInterface.getFavorite(id);
        getResep.enqueue(new Callback<GetResep>() {
            @Override
            public void onResponse(Call<GetResep> call, Response<GetResep> response) {
                dataset = response.body().getListDataResep();
                mAdapter = new FavoriteAdapter(dataset,getContext());
                rv.setAdapter(mAdapter);

                Log.d("Retrofit Get", "Jumlah data resep: " +String.valueOf(dataset.size()));
            }

            @Override
            public void onFailure(Call<GetResep> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

        rv.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rv, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Resep resep = dataset.get(posi);
                Intent i = new Intent(getContext(),detail.class);
                i.putExtra("id",resep.getId());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int posi) {

            }
        }));
        
        return view;
    }
}
