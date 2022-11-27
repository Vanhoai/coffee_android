package com.example.coffee.models.Shop;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GiftResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private Total total;

    public GiftResponse(String message, int code, Total total) {
        super(message, code);
        this.total = total;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "GiftResponse{" +
                "total=" + total +
                '}';
    }
}
