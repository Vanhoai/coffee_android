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
}
