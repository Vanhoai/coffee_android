package com.example.coffee.models.Others;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class MissionUser implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("current")
    private int current;

    @SerializedName("createdAt")
    private Date createdAt;

    @SerializedName("updatedAt")
    private Date updatedAt;

    @SerializedName("deletedAt")
    private boolean deletedAt;

    @SerializedName("user")
    private int user;

    @SerializedName("mission")
    private int mission;

    public MissionUser(int id, int current, Date createdAt, Date updatedAt, boolean deletedAt, int user, int mission) {
        this.id = id;
        this.current = current;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.user = user;
        this.mission = mission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(boolean deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getMission() {
        return mission;
    }

    public void setMission(int mission) {
        this.mission = mission;
    }

    @Override
    public String toString() {
        return "MissionUser{" +
                "id=" + id +
                ", current=" + current +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", user=" + user +
                ", mission=" + mission +
                '}';
    }
}
