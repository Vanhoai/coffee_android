package com.example.coffee.services;

import static com.example.coffee.interfaces.GiftInterfaceAPI.GIFT_URL;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.GiftCallback;
import com.example.coffee.callbacks.GiftOfUserCallback;
import com.example.coffee.callbacks.PromoCallback;
import com.example.coffee.interfaces.GiftInterfaceAPI;
import com.example.coffee.models.Shop.GiftResponse;
import com.example.coffee.models.Shop.PromoResponse;
import com.example.coffee.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GiftService {

    private final GiftInterfaceAPI API;

    public synchronized GiftInterfaceAPI getAPI() {
        return API;
    }

    public GiftService() {
        API = new Retrofit.Builder()
                .baseUrl(GIFT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GiftInterfaceAPI.class);
    }

    public void getGift(int limit, int id, GiftCallback callback) {
        try {
            getAPI().getGift(id, limit).enqueue(new Callback<GiftResponse>() {
                @Override
                public void onResponse(@NonNull Call<GiftResponse> call, @NonNull Response<GiftResponse> response) {
                    Logger.log("RESPONSE", response);
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                    } else {
                        callback.onFailed(false);
                    }

                }

                @Override
                public void onFailure(@NonNull Call<GiftResponse> call, @NonNull Throwable t) {
                    callback.onFailed(false);
                    Logger.log("ERROR", t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPromo(int limit, PromoCallback callback){
        getAPI().getPromo(limit).enqueue(new Callback<PromoResponse>() {
            @Override
            public void onResponse(@NonNull Call<PromoResponse> call, @NonNull Response<PromoResponse> response) {
                if (response.code() == 200){
                    callback.onSuccess(true, response.body());
                }else {
                    callback.onFailed(false);
                }

            }

            @Override
            public void onFailure(@NonNull Call<PromoResponse> call, @NonNull Throwable t) {
                callback.onFailed(false);
            }
        });
    }

   public void getGiftOfUser(int id, GiftOfUserCallback callback) {
        getAPI().getGiftOfUser(id).enqueue(new Callback<com.example.coffee.models.Order.GiftResponse>() {
            @Override
            public void onResponse(@NonNull Call<com.example.coffee.models.Order.GiftResponse> call, @NonNull Response<com.example.coffee.models.Order.GiftResponse> response) {
                if (response.code() == 200){
                    Logger.log("GIFTOFUSER", response);
                    callback.onSuccess(true, response.body());
                }else {
                    callback.onFailed(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.example.coffee.models.Order.GiftResponse> call, @NonNull Throwable t) {
                callback.onFailed(false);
                Logger.log("GIFTOFUSER", t);
            }
        });
   }
}