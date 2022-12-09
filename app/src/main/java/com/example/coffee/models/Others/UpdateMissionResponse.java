package com.example.coffee.models.Others;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateMissionResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private UpdateMission updateMission;

    public UpdateMissionResponse(String message, int code, UpdateMission updateMission) {
        super(message, code);
        this.updateMission = updateMission;
    }

    public UpdateMission getUpdateMission() {
        return updateMission;
    }

    public void setUpdateMission(UpdateMission updateMission) {
        this.updateMission = updateMission;
    }

    @Override
    public String toString() {
        return "UpdateMissionResponse{" +
                "updateMission=" + updateMission +
                '}';
    }
}
