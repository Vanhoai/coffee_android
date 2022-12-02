package com.example.coffee.callbacks;

import com.example.coffee.models.Product.CommentResponse;

public interface CommentCallback {
    public void onSuccess(boolean value, CommentResponse commentResponse);
    public void onFailed(boolean value);
}
