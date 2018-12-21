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

import com.example.asus.cookfun.Adapter.ResepAdapter;
import com.example.asus.cookfun.Listener.*;
import com.example.asus.cookfun.Model.GetResep;
import com.example.asus.cookfun.Model.Resep;
import com.example.asus.cookfun.R;
import com.example.asus.cookfun.Rest.*;
import com.example.asus.cookfun.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    private RecyclerView rv;
    private ResepAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Resep> dataset;
    private ApiInterface mApiInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

//        dataset = new ArrayList<>();
//        dataset.add(new Resep("https://pbs.twimg.com/profile_images/824716853989744640/8Fcd0bji_400x400.jpg","Rizky","https://selerasa.com/wp-content/uploads/2015/07/images_mancanegara_Resep_Kebab_00.jpg","Nasi Goreng","Masakan saya","2 hours ago"));
//        dataset.add(new Resep("https://pbs.twimg.com/profile_images/824716853989744640/8Fcd0bji_400x400.jpg","Rizky","https://selerasa.com/wp-content/uploads/2015/07/images_mancanegara_Resep_Kebab_00.jpg","Nasi Goreng","Masakan saya","2 hours ago"));
        rv = (RecyclerView) view.findViewById(R.id.recyclerHome);
        rv.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
//        mAdapter = new ResepAdapter(dataset,getActivity());
//        rv.setAdapter(mAdapter);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetResep> getResep = mApiInterface.getResep();
        getResep.enqueue(new Callback<GetResep>() {
            @Override
            public void onResponse(Call<GetResep> call, Response<GetResep> response) {
                dataset = response.body().getListDataResep();
                mAdapter = new ResepAdapter(dataset,getContext());
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

    public void initialize(){

    }
}
