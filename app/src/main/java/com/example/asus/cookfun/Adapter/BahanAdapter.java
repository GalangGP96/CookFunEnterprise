package com.example.asus.cookfun.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.cookfun.Model.Bahan;
import com.example.asus.cookfun.R;
import com.example.asus.cookfun.Rest.ApiClient;

import java.util.List;

public class BahanAdapter extends RecyclerView.Adapter<BahanAdapter.ViewHolder>{
    private List<Bahan> rvData;
    private Context mContext;

    public BahanAdapter(List<Bahan> inputData, Context mContext) {
        this.rvData = inputData;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // di tutorial ini kita hanya mengunakan data string untuk tiap item
        public TextView namaBahan;


        public ViewHolder(View v) {
            super(v);

            namaBahan = (TextView) v.findViewById(R.id.itemBahan);
        }

    }

    @Override
    public BahanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bahan,parent,false);
        //mengeset ukuran view, margin, padding, dan parameter layout lainnya
        BahanAdapter.ViewHolder vh = new BahanAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut

        Bahan dt = rvData.get(position);

        holder.namaBahan.setText(dt.getNama_bahan());
    }

    @Override
    public int getItemCount(){
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }
}
