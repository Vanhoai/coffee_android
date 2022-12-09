package com.example.coffee.models.Others;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateMission implements Serializable {

    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private boolean error;

    public UpdateMission(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "UpdateMission{" +
                "message='" + message + '\'' +
                ", error=" + error +
                '}';
    }
}
