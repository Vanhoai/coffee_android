package com.example.coffee.models.Others;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MissionUserResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private MissionUser missionUser;

    public MissionUserResponse(String message, int code, MissionUser missionUser) {
        super(message, code);
        this.missionUser = missionUser;
    }

    public MissionUser getMissionUser() {
        return missionUser;
    }

    public void setMissionUser(MissionUser missionUser) {
        this.missionUser = missionUser;
    }

    @Override
    public String toString() {
        return "MissionUserResponse{" +
                "missionUser=" + missionUser +
                '}';
    }
}
