package com.example.coffee.callbacks;

import com.example.coffee.models.Product.ProductDetailResponse;

public interface ProductDetailCallback {
    public void onSuccess(boolean value, ProductDetailResponse productResponse);
    public void onFailed(boolean value);
}
