package com.example.asus.cookfun.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBahan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Bahan> dataBahan;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Bahan> getDataBahan() {
        return dataBahan;
    }

    public void setDataBahan(List<Bahan> dataBahan) {
        this.dataBahan = dataBahan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
