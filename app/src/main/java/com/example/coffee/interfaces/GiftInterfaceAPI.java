package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.Shop.GiftResponse;
import com.example.coffee.models.Shop.PromoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GiftInterfaceAPI {

    String GIFT_URL = String.format("%s/", BASE_URL);

    @GET("mission/user/{id}")
    Call<GiftResponse> getGift(
            @Path("id") int id,
            @Query("limit") int limit
    );

    @GET("mission/information")
    Call<PromoResponse> getPromo(
            @Query("limit") int limit
    );

    @GET("gift/user/{id}")
    Call<GiftResponse> getGiftOfUser();
}
