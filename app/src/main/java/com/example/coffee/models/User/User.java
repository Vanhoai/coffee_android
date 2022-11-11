package com.example.coffee.models.User;

import com.example.coffee.models.Shop.Shop;

import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String email;
    private String image;
    private ArrayList history;
    private int role;
    private int loginType;
    private float balance;
    private int exp;
    private ArrayList<Shop> favorite;
}
