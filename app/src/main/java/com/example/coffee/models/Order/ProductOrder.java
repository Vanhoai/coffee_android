package com.example.coffee.models.Order;

import com.example.coffee.models.Product.Product;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductOrder implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("count")
    private int count;

    @SerializedName("price")
    private Double price;

    @SerializedName("total")
    private Double total;

    @SerializedName("product")
    private Product product;

    public ProductOrder(int id, int count, Double price, Double total, Product product) {
        this.id = id;
        this.count = count;
        this.price = price;
        this.total = total;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", count=" + count +
                ", price=" + price +
                ", total=" + total +
                ", product=" + product +
                '}';
    }
}
