package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.Order.OrderBody;
import com.example.coffee.models.Order.OrderResponse;
import com.example.coffee.models.Product.ProductRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface OrderInterfaceAPI {
    String ORDER_URL = String.format("%s/order/", BASE_URL);

    @POST("?")
    @FormUrlEncoded()
    Call<OrderResponse> order(
            @Body OrderBody orderBody
    );
}
