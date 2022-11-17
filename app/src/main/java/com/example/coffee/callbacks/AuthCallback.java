package com.example.coffee.callbacks;

import com.example.coffee.models.User.UserResponse;

public interface AuthCallback {
    public void onSuccess(Boolean value, UserResponse userResponse);
    public void onFailed(Boolean value);
}
