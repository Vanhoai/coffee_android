package com.example.coffee.services;

import static com.example.coffee.interfaces.AuthInterfaceAPI.AUTH_URL;
import static com.example.coffee.interfaces.ShopInterfaceAPI.SHOP_URL;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.ShopCallback;
import com.example.coffee.interfaces.AuthInterfaceAPI;
import com.example.coffee.interfaces.ShopInterfaceAPI;
import com.example.coffee.models.Shop.ShopResponse;
import com.example.coffee.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopService {

    private ShopInterfaceAPI API;

    public ShopInterfaceAPI getAPI() {return API;}

    public ShopService() {
        API = new Retrofit.Builder()
                .baseUrl(SHOP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopInterfaceAPI.class);
    }

    public void getAllShop(ShopCallback callback) {
        try {
            getAPI().getAllShop().enqueue(new Callback<ShopResponse>() {
                @Override
                public void onResponse(@NonNull Call<ShopResponse> call, @NonNull Response<ShopResponse> response) {
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                    } else {
                        callback.onFailed(false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ShopResponse> call, @NonNull Throwable throwable) {
                    callback.onFailed(false);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void getShops(int limit, int skip, String sort, String field, ShopCallback callback) {
        try {
            getAPI().getShops(limit, skip, sort, field).enqueue(new Callback<ShopResponse>() {
                @Override
                public void onResponse(@NonNull Call<ShopResponse> call, @NonNull Response<ShopResponse> response) {
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                    } else {
                        callback.onFailed(false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ShopResponse> call, @NonNull Throwable throwable) {
                    callback.onFailed(false);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
