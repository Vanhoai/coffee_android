package com.example.coffee.models.Shop;

import java.io.Serializable;

public class Mission implements Serializable {
    private int id;
    private String description;
    private int count;
    private int expired;
    private String name;

    public Mission(int id, String description, int count, int expired, String name) {
        this.id = id;
        this.description = description;
        this.count = count;
        this.expired = expired;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
