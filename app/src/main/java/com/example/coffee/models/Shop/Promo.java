package com.example.coffee.models.Shop;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Promo implements Serializable {

    @SerializedName("hottest")
    private Mission hottest;

    @SerializedName("missions")
    private ArrayList<Mission> missions;

    public Promo(Mission hottest, ArrayList<Mission> missions) {
        this.hottest = hottest;
        this.missions = missions;
    }

    public Mission getHottest() {
        return hottest;
    }

    public void setHottest(Mission hottest) {
        this.hottest = hottest;
    }

    public ArrayList<Mission> getMissions() {
        return missions;
    }

    public void setMissions(ArrayList<Mission> missions) {
        this.missions = missions;
    }

    @Override
    public String toString() {
        return "Promo{" +
                "hottest=" + hottest +
                ", missions=" + missions +
                '}';
    }
}
