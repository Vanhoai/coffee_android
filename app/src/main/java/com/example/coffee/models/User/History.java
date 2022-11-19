package com.example.coffee.models.User;

import com.example.coffee.models.Order.Order;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;

public class History implements Serializable {

    private User user;
    private ArrayList<Order> orders;
    private BigInteger createdAt;
    private String description;
    private String nameHistory;
    private String priceHistory;
    private String dateHistory;

//    public History(User user, ArrayList<Order> orders, BigInteger createdAt) {
//        this.user = user;
//        this.orders = orders;
//        this.createdAt = createdAt;
//
//    }
    public History(String dateHistory,String description, String nameHistory, String priceHistory) {
        this.dateHistory = dateHistory;
        this.description = description;
        this.nameHistory = nameHistory;
        this.priceHistory = priceHistory;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameHistory() {
        return nameHistory;
    }

    public void setNameHistory(String nameHistory) {
        this.nameHistory = nameHistory;
    }

    public String getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(String priceHistory) {
        this.priceHistory = priceHistory;
    }

    public String getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(String dateHistory) {
        this.dateHistory = dateHistory;
    }

    @Override
    public String toString() {
        return "History{" +
                "user=" + user +
                ", orders=" + orders +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", nameHistory='" + nameHistory + '\'' +
                ", priceHistory='" + priceHistory + '\'' +
                '}';
    }
}
