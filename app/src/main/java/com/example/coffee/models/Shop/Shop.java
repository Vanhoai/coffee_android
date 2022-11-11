package com.example.coffee.models.Shop;

public class Shop {
    private int id;
    private String address;
    private String description;
    private String image;
    private float X;
    private float Y;

    public Shop(int id, String address, String description, String image, float x, float y) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.image = image;
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
}
