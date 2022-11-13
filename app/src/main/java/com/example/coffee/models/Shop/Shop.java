package com.example.coffee.models.Shop;

import com.example.coffee.models.Product.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable {
    private int id;
    private String address;
    private String description;
    private String image;
    private ArrayList<Product> products;
    private float X;
    private float Y;

    public Shop(int id, String address, String description, String image, ArrayList<Product> products, float x, float y) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.image = image;
        this.products = products;
        X = x;
        Y = y;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", products=" + products +
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }
}
