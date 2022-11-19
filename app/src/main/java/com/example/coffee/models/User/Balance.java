package com.example.coffee.models.User;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Balance implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("amount")
    private Float amount;

    public Balance(int id, String code, Float amount) {
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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", amount=" + amount +
                '}';
    }
}
