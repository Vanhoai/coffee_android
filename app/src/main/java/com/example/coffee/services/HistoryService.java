package com.example.coffee.services;

import static com.example.coffee.interfaces.HistoryInterfaceAPI.HISTORY_URL;
import static com.example.coffee.interfaces.ProductInterfaceAPI.PRODUCT_URL;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.HistoryCallback;
import com.example.coffee.interfaces.HistoryInterfaceAPI;
import com.example.coffee.interfaces.ProductInterfaceAPI;
import com.example.coffee.models.User.HistoryResponse;
import com.example.coffee.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryService {

    private final HistoryInterfaceAPI API;

    public synchronized HistoryInterfaceAPI getAPI() { return this.API; }

    public HistoryService() {
        API = new Retrofit.Builder()
                .baseUrl(HISTORY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HistoryInterfaceAPI.class);

    }

    public void getAllHistoryOfUser(int id, HistoryCallback callback) {
        try {
            getAPI().getAllHistoryOfUser(id).enqueue(new Callback<HistoryResponse>() {
                @Override
                public void onResponse(@NonNull Call<HistoryResponse> call, @NonNull Response<HistoryResponse> response) {
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                    } else {
                        callback.onFailed(false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<HistoryResponse> call, @NonNull Throwable t) {
                    callback.onFailed(false);
                    Logger.log("ERROR", t);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
