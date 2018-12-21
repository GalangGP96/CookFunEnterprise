package com.example.asus.cookfun.Model;

public class Bahan {
    String id_resep,nama_bahan;

    public Bahan(String id_resep, String nama_bahan) {
        this.id_resep = id_resep;
        this.nama_bahan = nama_bahan;
    }

    public String getId_resep() {
        return id_resep;
    }

    public void setId_resep(String id_resep) {
        this.id_resep = id_resep;
    }

    public String getNama_bahan() {
        return nama_bahan;
    }

    public void setNama_bahan(String nama_bahan) {
        this.nama_bahan = nama_bahan;
    }
}
