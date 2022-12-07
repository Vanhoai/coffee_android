package com.example.coffee.models.User;

import android.database.DatabaseErrorHandler;

import com.example.coffee.models.Order.Order;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class History implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("price")
    private float price;

    @SerializedName("updatedAt")
    private Date date;

    @SerializedName("user")
    private User user;

    @SerializedName("order")
    private Order order;

    public History(int id, float price, Date date, User user, Order order) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.user = user;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                ", user=" + user +
                ", order=" + order +
                '}';
    }
}
