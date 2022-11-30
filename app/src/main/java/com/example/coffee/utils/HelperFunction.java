package com.example.coffee.utils;

import com.example.coffee.R;
import com.example.coffee.app.Data;

import java.util.Calendar;
import java.util.Date;

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

    public static String getDifferenceHour(Date date) {
        Date currentTime = Calendar.getInstance().getTime();
        long exp = date.getTime();
        long current = currentTime.getTime();
        long difference = exp - current;
        double time = difference * 1.0 / (1000 * 3600);
        // time is hour
        if (time > 48) {
            return String.format("Ends in %d day", (int) time / 24);
        }
        if (time < 1) {
            return String.format("Ends in %d minutes", (int) time * 60);
        }
        return String.format("Ends in %d hour", (int) time);
    }
}
