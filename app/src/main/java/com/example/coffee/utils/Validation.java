package com.example.coffee.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean verifyEmail(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean verifyPhoneNumber(String phone) {
        String value = "";
        if (phone.charAt(0) == '0') {
            value = String.format("+84%s", phone.substring(1));
        } else if (phone.startsWith("84")) {
            value = String.format("+%s", phone);
        }

        return value.length() == 12;
    }

    public static boolean verifyLogin(String email, String password) {
        if (email.trim().length() <= 0 || password.trim().length() <= 0) {
            return false;
        }
        return verifyEmail(email);
    }

    public static boolean verifyRegister(String username, String email, String password, String confirmPassword) {
        if (username.trim().length() <= 0 || email.trim().length() <= 0 || password.trim().length() <= 0 || confirmPassword.trim().length() <= 0) {
            return false;
        }
        if (!verifyEmail(email)) return false;
        return password.equals(confirmPassword);
    }
}
