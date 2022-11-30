package com.example.coffee.models.Order;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GiftResponse extends HttpResponse implements Serializable {
    @SerializedName("data")
    private ArrayList<Gift> gifts;

    public GiftResponse(String message, int code, ArrayList<Gift> gifts) {
        super(message, code);
        this.gifts = gifts;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    @Override
    public String toString() {
        return "GiftResponse{" +
                "gifts=" + gifts +
                '}';
    }
}
