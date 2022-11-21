package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.Shop.ShopResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopInterfaceAPI {

    String SHOP_URL = String.format("%s/shops/", BASE_URL);

    @GET("all")
    Call<ShopResponse> getAllShop();

    @GET("?")
    Call<ShopResponse> getShops(
        @Query("limit") int limit,
        @Query("skip") int skip,
        @Query("sort") String sort
    );
}
