package com.example.coffee.models.Order;

import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.models.User.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("address")
    private String address;

    @SerializedName("shop")
    private Shop shop;

    @SerializedName("total")
    private Double total;

    @SerializedName("status")
    private int status;

    @SerializedName("user")
    private User user;

    @SerializedName("products")
    private ArrayList<ProductOrder> products;

    public Order(int id, String address, Shop shop, Double total, int status, User user, ArrayList<ProductOrder> products) {
        this.id = id;
        this.address = address;
        this.shop = shop;
        this.total = total;
        this.status = status;
        this.user = user;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductOrder> products) {
        this.products = products;
    }

    public void assign(Order order) {
        this.id = order.id;
        this.address = order.address;
        this.shop = order.shop;
        this.total = order.total;
        this.status = order.status;
        this.user = order.user;
        this.products = order.products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", shop=" + shop +
                ", total=" + total +
                ", status=" + status +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
