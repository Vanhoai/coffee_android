package com.example.coffee.models.Product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    @SerializedName("price")
    private float price;

    @SerializedName("explored")
    private int explored;

    @SerializedName("quantity")
    private int quantity;

    private int current;

    @SerializedName("rating")
    private float rating;

    @SerializedName("description")
    private String description;

    public Product(int id, String name, String image, float price, int explored, int quantity, int current, float rating, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.explored = explored;
        this.quantity = quantity;
        this.current = current;
        this.rating = rating;
        this.description = description;
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

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", explored=" + explored +
                ", quantity=" + quantity +
                ", current=" + current +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
