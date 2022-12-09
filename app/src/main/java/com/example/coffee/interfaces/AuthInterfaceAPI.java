package com.example.coffee.interfaces;


import com.example.coffee.models.Others.MailResponse;
import com.example.coffee.models.User.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthInterfaceAPI extends BaseAPI{

    String AUTH_URL = String.format("%s/auth/", BASE_URL);

    @POST("login")
    @FormUrlEncoded
    Call<UserResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("register")
    @FormUrlEncoded
    Call<UserResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    @POST("reset")
    @FormUrlEncoded
    Call<UserResponse> resetPassword(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("send-code")
    @FormUrlEncoded
    Call<MailResponse> sendCode(
            @Field("email") String email,
            @Field("code") String code
    );

}
