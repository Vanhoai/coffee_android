package com.example.coffee.models.Order;

import com.example.coffee.models.Product.Product;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("address")
    private String address;

    @SerializedName("total")
    private Double total;

    @SerializedName("status")
    private int status;

    public Order(int id, String address, Double total, int status) {
        this.id = id;
        this.address = address;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
