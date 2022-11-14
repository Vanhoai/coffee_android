package com.example.coffee.services;

import static com.example.coffee.interfaces.AuthInterfaceAPI.AUTH_URL;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.interfaces.AuthInterfaceAPI;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthService {

    public AuthInterfaceAPI API;

    public synchronized AuthInterfaceAPI getAPI() {
        return API;
    }

    public AuthService() {
        API = new Retrofit.Builder()
                .baseUrl(AUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthInterfaceAPI.class);
    }

    public void login(String email, String password, AuthCallback callback) {
        try {
            getAPI().login(email, password).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                    Logger.log("RESPONSE", response);
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                    } else {
                        Logger.log("ERROR", "ERROR");
                        callback.onFailed(false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable throwable) {
                    Log.e("ERROR", throwable.toString());
                    callback.onFailed(false);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void register(String username, String email, String password, AuthCallback callback) {
        try {
            getAPI().register(username, email, password).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                    Logger.log("RESPONSE", response);
                    if (response.code() == 200) {
                        Logger.log("CODE", response.code());
                        callback.onSuccess(true, response.body());
                    } else {
                        callback.onFailed(false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable throwable) {
                    callback.onFailed(false);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
