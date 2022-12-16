package com.example.coffee.models.Order;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GiftResponseRemove extends HttpResponse implements Serializable {

    @SerializedName("data")
    private Gift gift;

    public GiftResponseRemove(String message, int code, Gift gift) {
        super(message, code);
        this.gift = gift;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    @Override
    public String toString() {
        return "GiftResponseRemove{" +
                "gift=" + gift +
                '}';
    }
}
