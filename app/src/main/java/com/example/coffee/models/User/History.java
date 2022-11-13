package com.example.coffee.models.User;

import com.example.coffee.models.Order.Order;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;

public class History implements Serializable {

    private User user;
    private ArrayList<Order> orders;
    private BigInteger createdAt;

    public History(User user, ArrayList<Order> orders, BigInteger createdAt) {
        this.user = user;
        this.orders = orders;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public BigInteger getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(BigInteger createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "History{" +
                "user=" + user +
                ", orders=" + orders +
                ", createdAt=" + createdAt +
                '}';
    }
}
