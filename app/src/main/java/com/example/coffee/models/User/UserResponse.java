package com.example.coffee.models.User;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserResponse extends HttpResponse implements Serializable{

    @SerializedName("data")
    private User user;

    public UserResponse(String message, int code, User user) {
        super(message, code);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                '}';
    }
}
