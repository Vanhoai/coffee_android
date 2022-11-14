package com.example.coffee.models.User;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("id")
    private int id;
    
    @SerializedName("username")
    private String username;
    
    @SerializedName("email")
    private String email;
    
    @SerializedName("role")
    private String role;
    
    @SerializedName("typeAccount")
    private String typeAccount;
    
    @SerializedName("balance")
    private float balance;
    
    @SerializedName("exp")
    private int exp;
    
    @SerializedName("image")
    private String image;
    
    @SerializedName("history")
    private int history;
    
    @SerializedName("favorite")
    private int favorite;
    
    @SerializedName("accessToken")
    private String accessToken;
    
    @SerializedName("refreshToken")
    private String refreshToken;
    

    public User(int id, String username, String email, String role, String typeAccount, float balance, int exp, String image, int history, int favorite, String accessToken, String refreshToken) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.typeAccount = typeAccount;
        this.balance = balance;
        this.exp = exp;
        this.image = image;
        this.history = history;
        this.favorite = favorite;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
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

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", typeAccount='" + typeAccount + '\'' +
                ", balance=" + balance +
                ", exp=" + exp +
                ", image='" + image + '\'' +
                ", history=" + history +
                ", favorite=" + favorite +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
