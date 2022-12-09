package com.example.coffee.callbacks;

import com.example.coffee.models.Others.MissionUser;
import com.example.coffee.models.Others.MissionUserResponse;

public interface MissionUseCallback {
    public void onSuccess(MissionUserResponse missionUserResponse);
    public void onFailed(boolean value);
}
