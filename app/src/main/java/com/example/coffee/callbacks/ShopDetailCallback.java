package com.example.coffee.callbacks;

import com.example.coffee.models.Shop.ShopDetailResponse;

public interface ShopDetailCallback {
    public void onSuccess(boolean value, ShopDetailResponse shopDetailResponse);
    public void onFailed(boolean value);
}
