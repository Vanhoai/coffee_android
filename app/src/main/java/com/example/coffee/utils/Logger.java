package com.example.coffee.utils;

import android.util.Log;

public class Logger {

    public static void log(Object title, Object content) {
        Log.d("TITLE", String.format("========================================== %s =====================================", title.toString()));
        Log.d("CONTENT", String.format("%s", content.toString()));
        Log.d("TITLE", String.format("========================================== %s =====================================", title.toString()));
    }


}
