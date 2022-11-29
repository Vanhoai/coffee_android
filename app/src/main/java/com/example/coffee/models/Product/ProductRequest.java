package com.example.coffee.models.Product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductRequest implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("quantity")
    private int quantity;

    public ProductRequest(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
