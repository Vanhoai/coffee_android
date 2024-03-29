package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.User.BalanceResponse;
import com.example.coffee.models.User.UserResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserInterfaceAPI {

    String USER_URL = String.format("%s/users/", BASE_URL);

    @PUT("{id}")
    @Multipart
    Call<UserResponse> uploadAvatar(
            @Path("id") int id,
            @Part MultipartBody.Part image
    );


    @PATCH("{id}")
    @FormUrlEncoded
    Call<BalanceResponse> topUp(
            @Path("id") int id,
            @Field("code") String code,
            @Field("balance") Float balance
    );

    @PUT("update/{id}")
    @FormUrlEncoded
    Call<UserResponse> updatePhoneNumber(
            @Path("id") int id,
            @Field("phone") String phone
    );

    @PUT("token/{id}")
    @FormUrlEncoded
    Call<UserResponse> updateDeviceToken(
            @Path("id") int id,
            @Field("token") String token
    );

    @GET("balance/{id}")
    Call<BalanceResponse> getBalance(
            @Path("id") int id
    );
}
