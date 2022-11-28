package com.example.coffee.callbacks;

import com.example.coffee.models.Product.ProductResponse;

public interface ProductCallback {
    public void onSuccess(boolean value, ProductResponse productResponse);
    public void onFailed(boolean value);
}
