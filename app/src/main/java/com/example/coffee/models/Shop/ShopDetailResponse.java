package com.example.coffee.models.Shop;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShopDetailResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private ShopDetail shopDetail;

    public ShopDetailResponse(String message, int code, ShopDetail shopDetail) {
        super(message, code);
        this.shopDetail = shopDetail;
    }

    public ShopDetail getShopDetail() {
        return shopDetail;
    }

    public void setShopDetail(ShopDetail shopDetail) {
        this.shopDetail = shopDetail;
    }

    @Override
    public String toString() {
        return "ShopDetailResponse{" +
                "shopDetail=" + shopDetail +
                '}';
    }
}
