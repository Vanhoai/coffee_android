package com.example.coffee.models.User;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Balance implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("amount")
    private Double amount;

    public Balance(int id, String code, Double amount) {
        this.id = id;
        this.code = code;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
