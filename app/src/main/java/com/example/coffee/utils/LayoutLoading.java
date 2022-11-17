package com.example.coffee.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.coffee.R;

public class LayoutLoading {

    static ConstraintLayout parent;
    static Context context;
    static View view;

    public LayoutLoading(ConstraintLayout parent, Context context) {
        LayoutLoading.parent = parent;
        LayoutLoading.context = context;
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        LayoutLoading.view = layoutInflater.inflate(R.layout.layout_loading, parent, false);
        LayoutLoading.parent.addView(LayoutLoading.view);
        LayoutLoading.view.setVisibility(View.GONE);
    }

    public static void setLoading() {
        LayoutLoading.view.setVisibility(View.VISIBLE);
    }

    public static void setGone() {
        LayoutLoading.view.setVisibility(View.GONE);
    }
}
