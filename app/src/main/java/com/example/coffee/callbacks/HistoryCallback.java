package com.example.coffee.callbacks;

import com.example.coffee.models.User.HistoryResponse;

public interface HistoryCallback {
    public void onSuccess(Boolean value, HistoryResponse historyResponse);
    public void onFailed(Boolean value);
}
