package com.example.coffee.services;

import static com.example.coffee.interfaces.AuthInterfaceAPI.AUTH_URL;
import static com.example.coffee.interfaces.UserInterfaceAPI.USER_URL;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.callbacks.BalanceCallback;
import com.example.coffee.interfaces.AuthInterfaceAPI;
import com.example.coffee.interfaces.UserInterfaceAPI;
import com.example.coffee.models.User.BalanceResponse;
import com.example.coffee.models.User.User;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserService {

    private final UserInterfaceAPI API;

    public synchronized UserInterfaceAPI getAPI() {
        return API;
    }

    public UserService() {
        API = new Retrofit.Builder()
                .baseUrl(USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserInterfaceAPI.class);
    }

    public void uploadAvatar(String accessToken, int id, File file, AuthCallback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        Logger.log("ACCESS TOKEN", accessToken);
        Logger.log("ID", id);
        Logger.log("FILE", file);

        getAPI().uploadAvatar(id, part).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if (response.code() == 200) {
                    callback.onSuccess(true, response.body());
                    Logger.log("RESPONSE", response);
                } else {
                    callback.onFailed(false);
                    Logger.log("ERROR", "REQUEST ERROR");
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable throwable) {
                callback.onFailed(false);
            }
        });
    }
    public  void  topUp(int id, String code, Float balance, BalanceCallback callback){
        try {
            getAPI().topUp(id, code, balance).enqueue(new Callback<BalanceResponse>() {
                @Override
                public void onResponse(@NonNull Call<BalanceResponse> call, @NonNull Response<BalanceResponse> response) {
                    if (response.code() == 200){
                        callback.onSuccess(true, response.body());
                        Logger.log("RESPONSE", response);
                    }
                    else {
                        callback.onFailed(false);
                        Logger.log("ERROR", "REQUEST ERROR");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<BalanceResponse> call, @NonNull Throwable t) {
                    callback.onFailed(false);
                    Logger.log("Error",t);
                }
            });
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void updatePhoneNumber(int id, String phone, AuthCallback callback) {
        try {
            getAPI().updatePhoneNumber(id, phone).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                    } else {
                        callback.onFailed(false);
                        Logger.log("O DAY HA BA", "UH");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                    callback.onFailed(false);
                    Logger.log("ERROR", t);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void updateDeviceToken(int id, String deviceToken, AuthCallback callback) {
        try {
            getAPI().updateDeviceToken(id, deviceToken).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                    } else {
                        callback.onFailed(false);
                        Logger.log("O DAY HA BA", "UH");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                    callback.onFailed(false);
                    Logger.log("ERROR", t);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
