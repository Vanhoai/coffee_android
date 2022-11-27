package com.example.coffee.models.Order;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Type implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("percent")
    private float percent;

    public Type(int id, float percent) {
        this.id = id;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", percent=" + percent +
                '}';
    }
}
