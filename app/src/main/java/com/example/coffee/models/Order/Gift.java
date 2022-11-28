package com.example.coffee.models.Order;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class Gift implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("code")
    private String code;

    @SerializedName("count")
    private int count;

    @SerializedName("expiredAt")
    private Date expiredAt;

    @SerializedName("type")
    private Type type;

    public Gift(int id, String name, String code, int count, Date expiredAt, Type type) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.count = count;
        this.expiredAt = expiredAt;
        this.type = type;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", count=" + count +
                ", expiredAt=" + expiredAt +
                ", type=" + type +
                '}';
    }
}
