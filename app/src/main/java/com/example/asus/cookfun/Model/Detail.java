package com.example.asus.cookfun.Model;

public class Detail {
    String judul_resep,deskripsi,foto_resep,username,photo;

    public Detail(String judul_resep, String deskripsi, String foto_resep, String username, String photo) {
        this.judul_resep = judul_resep;
        this.deskripsi = deskripsi;
        this.foto_resep = foto_resep;
        this.username = username;
        this.photo = photo;
    }

    public String getJudul_resep() {
        return judul_resep;
    }

    public void setJudul_resep(String judul_resep) {
        this.judul_resep = judul_resep;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto_resep() {
        return foto_resep;
    }

    public void setFoto_resep(String foto_resep) {
        this.foto_resep = foto_resep;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
