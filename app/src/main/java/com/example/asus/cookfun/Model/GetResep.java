package com.example.asus.cookfun.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetResep {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Resep> listDataResep;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Resep> getListDataResep() {
        return listDataResep;
    }

    public void setListDataResep(List<Resep> listDataResep) {
        this.listDataResep = listDataResep;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
