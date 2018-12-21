package com.example.asus.cookfun.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDetail {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Detail> dataResep;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Detail> getDataResep() {
        return dataResep;
    }

    public void setDataResep(List<Detail> dataResep) {
        this.dataResep = dataResep;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
