package com.example.asus.cookfun.Model;

public class Langkah {
    String id_resep,urutan,deskripsi_langkah,foto_langkah;

    public Langkah(String id_resep, String urutan, String deskripsi_langkah, String foto_langkah) {
        this.id_resep = id_resep;
        this.urutan = urutan;
        this.deskripsi_langkah = deskripsi_langkah;
        this.foto_langkah = foto_langkah;
    }

    public String getId_resep() {
        return id_resep;
    }

    public void setId_resep(String id_resep) {
        this.id_resep = id_resep;
    }

    public String getUrutan() {
        return urutan;
    }

    public void setUrutan(String urutan) {
        this.urutan = urutan;
    }

    public String getDeskripsi_langkah() {
        return deskripsi_langkah;
    }

    public void setDeskripsi_langkah(String deskripsi_langkah) {
        this.deskripsi_langkah = deskripsi_langkah;
    }

    public String getFoto_langkah() {
        return foto_langkah;
    }

    public void setFoto_langkah(String foto_langkah) {
        this.foto_langkah = foto_langkah;
    }
}
