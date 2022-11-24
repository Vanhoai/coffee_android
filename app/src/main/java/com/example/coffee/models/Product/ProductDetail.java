package com.example.coffee.models.Product;

import com.example.coffee.models.User.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductDetail implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private float price;

    @SerializedName("explored")
    private int explored;

    @SerializedName("rating")
    private int rating;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("image")
    private String image;

    @SerializedName("comments")
    private ArrayList<Comment> comments;

    public ProductDetail(int id, String name, String description, float price, int explored, int rating, int quantity, String image, ArrayList<Comment> comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.explored = explored;
        this.rating = rating;
        this.quantity = quantity;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", explored=" + explored +
                ", rating=" + rating +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", comments=" + comments +
                '}';
    }
}
