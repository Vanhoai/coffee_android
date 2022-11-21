package com.example.coffee.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HttpResponse implements Serializable {

    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    public HttpResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
