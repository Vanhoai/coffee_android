package com.example.coffee.models.Product;

import java.util.ArrayList;

public class Product {
    private int id;
    private String name;
    private String image;
    private float price;
    private int explored;
    private int quantity;
    private ArrayList<Comment> comments;

    public Product(int id, String name, String image, float price, int explored, int quantity, ArrayList<Comment> comments) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.explored = explored;
        this.quantity = quantity;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getExplored() {
        return explored;
    }

    public void setExplored(int explored) {
        this.explored = explored;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
