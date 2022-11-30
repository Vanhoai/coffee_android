package com.example.coffee.models.Order;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private Order order;

    public OrderResponse(String message, int code, Order order) {
        super(message, code);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
