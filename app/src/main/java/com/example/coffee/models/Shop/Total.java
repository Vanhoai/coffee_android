package com.example.coffee.models.Shop;

import com.example.coffee.models.Order.Gift;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Total implements Serializable {

    @SerializedName("totalGift")
    private int totalGift;

    @SerializedName("totalMission")
    private int totalMission;

    @SerializedName("totalMissionProgress")
    private int totalMissionProgress;

   @SerializedName("listGift")
    private ArrayList<Gift> listGift;

   @SerializedName("listMission")
    private ArrayList<Mission> listMission;

    public Total(int totalGift, int totalMission, int totalMissionProgress, ArrayList<Gift> listGift, ArrayList<Mission> listMission) {
        this.totalGift = totalGift;
        this.totalMission = totalMission;
        this.totalMissionProgress = totalMissionProgress;
        this.listGift = listGift;
        this.listMission = listMission;
    }

    public int getTotalGift() {
        return totalGift;
    }

    public void setTotalGift(int totalGift) {
        this.totalGift = totalGift;
    }

    public int getTotalMission() {
        return totalMission;
    }

    public void setTotalMission(int totalMission) {
        this.totalMission = totalMission;
    }

    public int getTotalMissionProgress() {
        return totalMissionProgress;
    }

    public void setTotalMissionProgress(int totalMissionProgress) {
        this.totalMissionProgress = totalMissionProgress;
    }

    public ArrayList<Gift> getListGift() {
        return listGift;
    }

    public void setListGift(ArrayList<Gift> listGift) {
        this.listGift = listGift;
    }

    public ArrayList<Mission> getListMission() {
        return listMission;
    }

    public void setListMission(ArrayList<Mission> listMission) {
        this.listMission = listMission;
    }

    @Override
    public String toString() {
        return "Total{" +
                "totalGift=" + totalGift +
                ", totalMission=" + totalMission +
                ", totalMissionProgress=" + totalMissionProgress +
                ", listGift=" + listGift +
                ", listMission=" + listMission +
                '}';
    }
}
