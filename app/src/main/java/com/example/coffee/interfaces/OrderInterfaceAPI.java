package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import retrofit2.Call;
import retrofit2.http.POST;

public interface OrderInterfaceAPI {
    String ORDER_URL = String.format("%s/order/", BASE_URL);


}
