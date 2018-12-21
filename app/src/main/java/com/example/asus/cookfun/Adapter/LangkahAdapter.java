package com.example.asus.cookfun.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.cookfun.Model.Bahan;
import com.example.asus.cookfun.Model.Langkah;
import com.example.asus.cookfun.R;

import java.util.List;

public class LangkahAdapter extends RecyclerView.Adapter<LangkahAdapter.ViewHolder>{
    private List<Langkah> rvData;
    private Context mContext;

    public LangkahAdapter(List<Langkah> inputData, Context mContext) {
        this.rvData = inputData;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // di tutorial ini kita hanya mengunakan data string untuk tiap item
        public TextView urutan;
        public TextView detailLangkah;

        public ViewHolder(View v) {
            super(v);

            urutan = (TextView) v.findViewById(R.id.lblUrutan);
            detailLangkah = (TextView) v.findViewById(R.id.itemLangkah);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_langkah,parent,false);
        //mengeset ukuran view, margin, padding, dan parameter layout lainnya
        LangkahAdapter.ViewHolder vh = new LangkahAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut

        Langkah dt = rvData.get(position);

        holder.urutan.setText(dt.getUrutan());
        holder.detailLangkah.setText(dt.getDeskripsi_langkah());
    }

    @Override
    public int getItemCount(){
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }
}
