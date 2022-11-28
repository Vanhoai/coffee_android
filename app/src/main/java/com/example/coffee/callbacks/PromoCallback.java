package com.example.coffee.callbacks;

import com.example.coffee.models.Shop.PromoResponse;

public interface PromoCallback {
    public void onSuccess(boolean value, PromoResponse promoResponse);
    public void onFailed(boolean value);
}
