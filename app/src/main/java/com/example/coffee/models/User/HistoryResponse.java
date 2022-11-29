package com.example.coffee.models.User;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private ArrayList<History> histories;

    public HistoryResponse(String message, int code, ArrayList<History> histories) {
        super(message, code);
        this.histories = histories;
    }

    public ArrayList<History> getHistories() {
        return histories;
    }

    public void setHistories(ArrayList<History> histories) {
        this.histories = histories;
    }

    @Override
    public String toString() {
        return "HistoryResponse{" +
                "histories=" + histories +
                '}';
    }
}
