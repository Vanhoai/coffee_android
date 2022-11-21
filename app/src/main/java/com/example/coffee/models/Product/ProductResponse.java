package com.example.coffee.models.Product;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private ArrayList<Product> products;

    public ProductResponse(String message, int code, ArrayList<Product> products) {
        super(message, code);
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "products=" + products +
                '}';
    }
}
