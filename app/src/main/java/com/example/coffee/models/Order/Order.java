package com.example.coffee.models.Order;

import com.example.coffee.models.Product.Product;

import java.util.ArrayList;

public class Order {
    private int id;
    private int userId;
    private float total;
    private String address;
    private ArrayList<Product> products;
    private ArrayList<Gift> gifts;

    public Order(int id, int userId, float total, String address, ArrayList<Product> products, ArrayList<Gift> gifts) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.address = address;
        this.products = products;
        this.gifts = gifts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }
}
