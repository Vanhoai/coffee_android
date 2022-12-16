package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.Order.GiftResponseRemove;
import com.example.coffee.models.Others.MissionUserResponse;
import com.example.coffee.models.Others.UpdateMissionResponse;
import com.example.coffee.models.Shop.GiftResponse;
import com.example.coffee.models.Shop.PromoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Call<com.example.coffee.models.Order.GiftResponse> getGiftOfUser(
            @Path("id") int id
    );

    @POST("gift/user/remove")
    @FormUrlEncoded()
    Call<GiftResponseRemove> removeGiftOfUser(
            @Field("userId") int userId,
            @Field("giftId") int giftId
    );

    @POST("mission/register")
    @FormUrlEncoded()
    Call<MissionUserResponse> registerMission(
            @Field("userId") int userId,
            @Field("missionId") int missionId
    );

    @POST("mission/update")
    @FormUrlEncoded()
    Call<UpdateMissionResponse> updateMissionUser(
            @Field("userId") int userId,
            @Field("missionId") int missionId,
            @Field("current") int current
    );
}
