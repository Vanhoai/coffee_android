package com.example.coffee.models.Product;

import com.example.coffee.models.User.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class Comment implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("rating")
    private float rating;

    @SerializedName("content")
    private String content;

    @SerializedName("user")
    private User user;

    @SerializedName("createdAt")
    private Date createdAt;

    public Comment(int id, float rating, String content, User user, Date createdAt) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}
