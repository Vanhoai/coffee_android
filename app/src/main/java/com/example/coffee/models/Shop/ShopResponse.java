package com.example.coffee.models.Shop;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private ArrayList<Shop> shops;

    public ShopResponse(String message, int code, ArrayList<Shop> shops) {
        super(message, code);
        this.shops = shops;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }

    @Override
    public String toString() {
        return "ShopResponse{" +
                "shops=" + shops +
                '}';
    }
}
