package com.example.coffee.models.Shop;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PromoResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private Promo promo;

    public PromoResponse(String message, int code, Promo promo) {
        super(message, code);
        this.promo = promo;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    @Override
    public String toString() {
        return "PromoResponse{" +
                "promo=" + promo +
                '}';
    }
}
