package com.example.coffee.models.User;

import com.example.coffee.models.Shop.Shop;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int id;
    private String username;
    private String email;
    private String image;
    private int history;
    private int role;
    private int loginType;
    private float balance;
    private int exp;
    private int favorite;

    public User(int id, String username, String email, String image, int history, int role, int loginType, float balance, int exp, int favorite) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = image;
        this.history = history;
        this.role = role;
        this.loginType = loginType;
        this.balance = balance;
        this.exp = exp;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", history=" + history +
                ", role=" + role +
                ", loginType=" + loginType +
                ", balance=" + balance +
                ", exp=" + exp +
                ", favorite=" + favorite +
                '}';
    }
}
