package com.example.asus.cookfun.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLangkah {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Langkah> dataLangkah;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Langkah> getDataLangkah() {
        return dataLangkah;
    }

    public void setDataLangkah(List<Langkah> dataLangkah) {
        this.dataLangkah = dataLangkah;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
