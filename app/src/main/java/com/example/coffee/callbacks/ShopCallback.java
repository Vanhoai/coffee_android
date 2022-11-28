package com.example.coffee.callbacks;

import com.example.coffee.models.Shop.ShopResponse;

public interface ShopCallback {
    public void onSuccess(boolean value, ShopResponse shopResponse);
    public void onFailed(boolean value);
}
