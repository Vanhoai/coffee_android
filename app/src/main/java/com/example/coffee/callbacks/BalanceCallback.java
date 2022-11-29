package com.example.coffee.callbacks;

import com.example.coffee.models.User.BalanceResponse;

public interface BalanceCallback {
    public void onSuccess(Boolean value, BalanceResponse balanceResponse);
    public void onFailed(Boolean value);
}
