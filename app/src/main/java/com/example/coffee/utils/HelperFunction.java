package com.example.coffee.utils;

import com.example.coffee.R;

public class HelperFunction {

    public static int getDrawable(float percent) {
        if (percent > 80) {
            return R.drawable.promo1;
        } else if (percent > 60) {
            return R.drawable.promo2;
        } else if (percent > 40) {
            return R.drawable.promo3;
        }else if (percent > 20) {
            return R.drawable.promo4;
        }
        return R.drawable.promo5;
    }
}
