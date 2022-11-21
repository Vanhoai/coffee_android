package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.Shop.ShopResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShopInterfaceAPI {

    String SHOP_URL = String.format("%s/shops/", BASE_URL);

    @GET("all")
    Call<ShopResponse> getAllShop();
}
