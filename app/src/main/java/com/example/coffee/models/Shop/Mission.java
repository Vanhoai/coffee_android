package com.example.coffee.models.Shop;

import com.example.coffee.models.Order.Type;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mission implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("mark")
    private float mark;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("total")
    private String total;

    @SerializedName("expiredAt")
    private int expiredAt;

    @SerializedName("current")
    private int current;

    @SerializedName("type")
    private Type type;

    public Mission(int id, float mark, String name, String description, String total, int expiredAt, int current, Type type) {
        this.id = id;
        this.mark = mark;
        this.name = name;
        this.description = description;
        this.total = total;
        this.expiredAt = expiredAt;
        this.current = current;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(int expiredAt) {
        this.expiredAt = expiredAt;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
