package com.example.coffee.utils;

import android.content.Context;
import android.util.Log;

import com.example.coffee.models.User.User;
import com.example.coffee.screens.auth.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class UserInformation {
    public static User getUser(Context context){
        Storage storage = new Storage(context);
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {}.getType();
        String json = storage.getItem("USER", "user");
        return gson.fromJson(json, type);
    }

    public static boolean setUser(Context context, User user) {
        Storage storage = new Storage(context);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        return storage.setItem("USER", "user", json);
    }
}
