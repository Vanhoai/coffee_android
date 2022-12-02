package com.example.coffee.services;

import static com.example.coffee.interfaces.OrderInterfaceAPI.ORDER_URL;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.OrderCallback;
import com.example.coffee.callbacks.OrderDetailCallback;
import com.example.coffee.interfaces.OrderInterfaceAPI;
import com.example.coffee.models.Order.OrderDetailResponse;
import com.example.coffee.models.Order.OrderResponse;
import com.example.coffee.models.Product.ProductRequest;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderService {

    private final OrderInterfaceAPI API;

    public OrderInterfaceAPI getAPI() {return API;}

    public OrderService() {
        API = new Retrofit.Builder()
                .baseUrl(ORDER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OrderInterfaceAPI.class);
    }

    public void createOrder(String address, int user, int product, int shop, int count, int order, OrderCallback callback) {
        try {
            getAPI().order(address, user, product, shop, count, order).enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(@NonNull Call<OrderResponse> call, @NonNull Response<OrderResponse> response) {
                    if (response.code() != 200) {
                        callback.onFailed(false);
                        return;
                    }
                    callback.onSuccess(true, response.body());
                }
                @Override
                public void onFailure(@NonNull Call<OrderResponse> call, @NonNull Throwable throwable) {
                    callback.onFailed(false);
                    Logger.log("ERROR", throwable);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void deleteOrder(int id, OrderCallback callback) {
        try {
            getAPI().remove(id).enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(@NonNull Call<OrderResponse> call, @NonNull Response<OrderResponse> response) {
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                        return;
                    }
                    callback.onFailed(false);
                }

                @Override
                public void onFailure(@NonNull Call<OrderResponse> call, @NonNull Throwable t) {
                    callback.onFailed(false);
                    Logger.log("RESPONSE", t);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void orderDetail(int id, OrderDetailCallback callback) {
        try {
            getAPI().detailOrder(id).enqueue(new Callback<OrderDetailResponse>() {
                @Override
                public void onResponse(@NonNull Call<OrderDetailResponse> call, @NonNull Response<OrderDetailResponse> response) {
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                        return;
                    }
                    callback.onFailed(false);
                }

                @Override
                public void onFailure(@NonNull Call<OrderDetailResponse> call, @NonNull Throwable t) {
                    callback.onFailed(false);
                    Logger.log("ERROR", t);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
