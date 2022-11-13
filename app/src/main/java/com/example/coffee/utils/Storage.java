package com.example.coffee.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.coffee.models.User.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Storage {

    private final Context context;

    public Storage(Context context) {
        this.context = context;
    }

    public boolean setItem(String name, String key, String value) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public String getItem(String name, String key) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, "");
        } catch (Exception exception) {
            return null;
        }
    }

    public boolean removeItem(String name, String key) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}
