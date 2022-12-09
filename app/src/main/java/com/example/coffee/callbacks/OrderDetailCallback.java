package com.example.coffee.callbacks;

import com.example.coffee.models.Order.OrderDetailResponse;

public interface OrderDetailCallback {
    public void onSuccess(boolean value, OrderDetailResponse orderDetailResponse);
    public void onFailed(boolean value);
}
