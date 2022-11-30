package com.example.coffee.services;

import static com.example.coffee.interfaces.HistoryInterfaceAPI.HISTORY_URL;
import static com.example.coffee.interfaces.OrderInterfaceAPI.ORDER_URL;

import com.example.coffee.interfaces.HistoryInterfaceAPI;
import com.example.coffee.interfaces.OrderInterfaceAPI;

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

    public void createOrder() {

    }

}
