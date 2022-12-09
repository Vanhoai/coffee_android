package com.example.coffee.models.Shop;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopDetail implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("location")
    private String location;

    @SerializedName("description")
    private String description;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("image")
    private String image;

    @SerializedName("products")
    private ArrayList<ProductDetail> products;

    public ShopDetail(int id, String location, String description, double longitude, double latitude, String image, ArrayList<ProductDetail> products) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.image = image;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<ProductDetail> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductDetail> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShopDetail{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", image='" + image + '\'' +
                ", products=" + products +
                '}';
    }
}
