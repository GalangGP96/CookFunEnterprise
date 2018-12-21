package com.example.asus.cookfun.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.cookfun.Model.Bahan;
import com.example.asus.cookfun.Model.Resep;
import com.example.asus.cookfun.R;
import com.example.asus.cookfun.Rest.ApiClient;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private List<Resep> rvData;
    private Context mContext;

    public FavoriteAdapter(List<Resep> inputData, Context mContext) {
        this.rvData = inputData;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // di tutorial ini kita hanya mengunakan data string untuk tiap item
        public TextView judulResep,namaPenulis;
        public ImageView fotoResep;
        public CircleImageView fotoPenulis;

        public ViewHolder(View v) {
            super(v);

            judulResep = (TextView) v.findViewById(R.id.faforitJudulResep);
            namaPenulis = (TextView) v.findViewById(R.id.faforitNamaPenulis);
            fotoResep = (ImageView) v.findViewById(R.id.faforitFotoResep);
            fotoPenulis = (CircleImageView) v.findViewById(R.id.faforitFotoPenulis);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_favorite,parent,false);
        //mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut

        Resep dt = rvData.get(position);

        holder.judulResep.setText(dt.getJudul_resep());
        holder.namaPenulis.setText(dt.getUsername());
        Glide.with(mContext).asBitmap().load(ApiClient.BASE_USER+dt.getPhoto()).apply(RequestOptions.placeholderOf(R.drawable.logo).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(holder.fotoPenulis);
        Glide.with(mContext).load(ApiClient.BASE_RESEP+dt.getFoto_resep()).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(holder.fotoResep);
    }

    @Override
    public int getItemCount(){
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }
}
