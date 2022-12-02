package com.example.coffee.models.Order;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderDetailResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private Order order;

    public OrderDetailResponse(String message, int code, Order order) {
        super(message, code);
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetailResponse{" +
                "order=" + order +
                '}';
    }
}
