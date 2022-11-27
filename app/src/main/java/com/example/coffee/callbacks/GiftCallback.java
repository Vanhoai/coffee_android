package com.example.coffee.callbacks;

import com.example.coffee.models.Shop.GiftResponse;

public interface GiftCallback {
    public  void onSuccess(boolean value, GiftResponse giftResponse);
    public  void onFailed(boolean value);
}
