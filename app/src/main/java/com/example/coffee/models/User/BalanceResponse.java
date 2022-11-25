package com.example.coffee.models.User;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BalanceResponse extends HttpResponse implements Serializable {
    @SerializedName("data")
    private Balance balance;

    public BalanceResponse(String message, int code, Balance balance) {
        super(message, code);
        this.balance = balance;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceResponse{" +
                "balance=" + balance +
                '}';
    }
}
