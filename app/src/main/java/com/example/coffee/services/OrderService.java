package com.example.coffee.services;

import static com.example.coffee.interfaces.HistoryInterfaceAPI.HISTORY_URL;
import static com.example.coffee.interfaces.OrderInterfaceAPI.ORDER_URL;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.OrderCallback;
import com.example.coffee.interfaces.HistoryInterfaceAPI;
import com.example.coffee.interfaces.OrderInterfaceAPI;
import com.example.coffee.models.Order.Order;
import com.example.coffee.models.Order.OrderBody;
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

    public void createOrder(int user, int shop, String address, ArrayList<ProductRequest> products, OrderCallback callback) {
        try {
            OrderBody orderBody = new OrderBody();
            orderBody.setUser(user);
            orderBody.setShop(shop);
            orderBody.setAddress(address);
            ArrayList<ProductRequest> productRequests = new ArrayList<>(products);
            orderBody.setProducts(productRequests);
            Logger.log("BODY", orderBody);
            getAPI().order(orderBody).enqueue(new Callback<OrderResponse>() {
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

}
