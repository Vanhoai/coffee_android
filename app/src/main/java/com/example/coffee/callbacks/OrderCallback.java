package com.example.coffee.callbacks;

import com.example.coffee.models.Order.OrderResponse;

public interface OrderCallback {
    public void onSuccess(boolean value, OrderResponse orderResponse);
    public void onFailed(boolean value);
}
