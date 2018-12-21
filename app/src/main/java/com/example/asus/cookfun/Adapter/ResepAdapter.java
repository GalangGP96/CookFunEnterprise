package com.example.asus.cookfun.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.cookfun.Model.*;
import com.example.asus.cookfun.*;
import com.example.asus.cookfun.Rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.ViewHolder>{
    private List<Resep> rvData;
    private Context mContext;

    public ResepAdapter(List<Resep> inputData, Context mContext) {
        this.rvData = inputData;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // di tutorial ini kita hanya mengunakan data string untuk tiap item
        public CircleImageView fotoUser;
        public TextView namaUser;
        public ImageView fotoResep;
        public TextView judulResep;
        public TextView deskripsiResep;
        public TextView waktuResep;


        public ViewHolder(View v) {
            super(v);

            fotoUser = (CircleImageView) v.findViewById(R.id.fotoPemilik);
            namaUser = (TextView) v.findViewById(R.id.namaPemilik);
            fotoResep = (ImageView) v.findViewById(R.id.fotoResep);
            judulResep = (TextView) v.findViewById(R.id.JudulResep);
            deskripsiResep = (TextView) v.findViewById(R.id.deskripsiResep);
            waktuResep = (TextView) v.findViewById(R.id.waktuResep);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_resep,parent,false);
        //mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut

        Resep dt = rvData.get(position);

        holder.namaUser.setText(dt.getUsername());
        holder.judulResep.setText(dt.getJudul_resep());
        holder.deskripsiResep.setText(dt.getDeskripsi());
        holder.waktuResep.setText(dt.getTanggal_post());

        Glide.with(mContext).asBitmap().load(ApiClient.BASE_USER+dt.getPhoto()).apply(RequestOptions.placeholderOf(R.drawable.logo).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(holder.fotoUser);
//        Glide.with(mContext).load(ApiClient.BASE_RESEP+dt.getFoto_resep()).apply(RequestOptions.overrideOf(640,480).fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(holder.fotoResep);
        Glide.with(mContext).load(ApiClient.BASE_RESEP+dt.getFoto_resep()).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(holder.fotoResep);

    }

    @Override
    public int getItemCount(){
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }
}
