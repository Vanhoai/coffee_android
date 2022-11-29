package com.example.coffee.models.User;

import android.database.DatabaseErrorHandler;

import com.example.coffee.models.Order.Order;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class History implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("price")
    private float price;

    @SerializedName("updatedAt")
    private Date date;

    public History(int id, float price, Date date) {
        this.id = id;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
