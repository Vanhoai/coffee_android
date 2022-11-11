package com.example.coffee.models.Order;

import java.math.BigInteger;

public class Gift {
    private int id;
    private int type;
    private String code;
    private int count;
    private BigInteger expire;

    public Gift(int id, int type, String code, int count, BigInteger expire) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.count = count;
        this.expire = expire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public BigInteger getExpire() {
        return expire;
    }

    public void setExpire(BigInteger expire) {
        this.expire = expire;
    }
}
