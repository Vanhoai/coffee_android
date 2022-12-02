package com.example.coffee.callbacks;

import com.example.coffee.models.Order.GiftResponse;

public interface GiftOfUserCallback {
    public void onSuccess(boolean value, GiftResponse giftResponse);
    public void onFailed(boolean value);

}
