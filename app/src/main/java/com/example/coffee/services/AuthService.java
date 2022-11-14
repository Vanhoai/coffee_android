package com.example.coffee.services;

import static com.example.coffee.interfaces.AuthInterfaceAPI.AUTH_URL;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.interfaces.AuthInterfaceAPI;
import com.example.coffee.models.User.UserResponse;

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
        getAPI().login(email, password).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                callback.onSuccess(true, response.body());
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable throwable) {
                Log.e("ERROR", throwable.toString());
                callback.onFailed(false);
            }
        });
    }
    public void register(String username, String email, String password, AuthCallback callback){
        getAPI().register(username, email, password).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                callback.onSuccess(true, response.body());
                Log.d("RESPONE", response.body().toString());
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                Log.e("ERROR", t.toString());
                callback.onFailed(false);
            }
        });
    }
}
