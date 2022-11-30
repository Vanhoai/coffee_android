package com.example.coffee.models.Order;

import com.example.coffee.models.Product.ProductRequest;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderBody implements Serializable {
    @SerializedName("user")
    private int user;

    @SerializedName("shop")
    private int shop;

    @SerializedName("address")
    private String address;

    @SerializedName("products")
    private ArrayList<ProductRequest> products;

    @SerializedName("gift")
    private int gift;

    public OrderBody() {}

    public OrderBody(int user, int shop, String address, ArrayList<ProductRequest> products, int gift) {
        this.user = user;
        this.shop = shop;
        this.address = address;
        this.products = products;
        this.gift = gift;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getShop() {
        return shop;
    }

    public void setShop(int shop) {
        this.shop = shop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<ProductRequest> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductRequest> products) {
        this.products = products;
    }

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }
}

