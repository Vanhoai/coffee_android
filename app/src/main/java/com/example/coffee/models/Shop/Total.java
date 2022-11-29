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

   @SerializedName("listGifts")
    private ArrayList<Gift> listGifts;

   @SerializedName("listMissions")
    private ArrayList<Mission> listMissions;


    public Total(int totalGift, int totalMission, int totalMissionProgress, ArrayList<Gift> listGifts, ArrayList<Mission> listMissions) {
        this.totalGift = totalGift;
        this.totalMission = totalMission;
        this.totalMissionProgress = totalMissionProgress;
        this.listGifts = listGifts;
        this.listMissions = listMissions;
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

    public ArrayList<Gift> getListGifts() {
        return listGifts;
    }

    public void setListGifts(ArrayList<Gift> listGifts) {
        this.listGifts = listGifts;
    }

    public ArrayList<Mission> getListMissions() {
        return listMissions;
    }

    public void setListMissions(ArrayList<Mission> listMissions) {
        this.listMissions = listMissions;
    }

    @Override
    public String toString() {
        return "Total{" +
                "totalGift=" + totalGift +
                ", totalMission=" + totalMission +
                ", totalMissionProgress=" + totalMissionProgress +
                ", listGifts=" + listGifts +
                ", listMissions=" + listMissions +
                '}';
    }
}
