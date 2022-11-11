package com.example.coffee.models.Product;

import com.example.coffee.models.User.User;

import java.math.BigInteger;

public class Comment {
    private int id;
    private User user;
    private BigInteger createAt;
    private String description;
    private float rating;

    public  Comment() {}

    public Comment(int id, User user, BigInteger createAt, String description, float rating) {
        this.id = id;
        this.user = user;
        this.createAt = createAt;
        this.description = description;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigInteger getCreateAt() {
        return createAt;
    }

    public void setCreateAt(BigInteger createAt) {
        this.createAt = createAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
