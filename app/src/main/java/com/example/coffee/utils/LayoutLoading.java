package com.example.coffee.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.coffee.R;

public class LayoutLoading {

    ConstraintLayout parent;
    Context context;
    View view;

    public LayoutLoading(ConstraintLayout parent, Context context) {
        this.parent = parent;
        this.context = context;
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        this.view = layoutInflater.inflate(R.layout.layout_loading, parent, false);
        this.parent.addView(this.view);
        this.view.setVisibility(View.GONE);
    }

    public void setLoading() {
        this.view.setVisibility(View.VISIBLE);
    }

    public void setGone() {
        this.view.setVisibility(View.GONE);
    }
}
