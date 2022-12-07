package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.Order.OrderDetailResponse;
import com.example.coffee.models.Order.OrderResponse;
import com.example.coffee.models.User.HistoryResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderInterfaceAPI {
    String ORDER_URL = String.format("%s/order/", BASE_URL);

    @PUT("update")
    @FormUrlEncoded()
    Call<OrderResponse> order(
            @Field("address") String address,
            @Field("user") int user,
            @Field("product") int product,
            @Field("shop") int shop,
            @Field("count") int count,
            @Field("order") int order
    );

    @PUT("{id}")
    @FormUrlEncoded()
    Call<OrderResponse> updatePriceOrder(
            @Path("id") int id,
            @Field("status") int status,
            @Field("balance") double balance
    );

    @PUT("{id}")
    @FormUrlEncoded()
    Call<OrderResponse> updateStatusOrder(
            @Path("id") int id,
            @Field("status") int status
    );

    @DELETE("delete/{id}")
    Call<OrderResponse> remove(
            @Path("id") int id
    );

    @GET("detail/{id}")
    Call<OrderDetailResponse> detailOrder(
            @Path("id") int id
    );

    @PATCH("{id}")
    Call<HistoryResponse> cancelOrder(
            @Path("id") int id
    );
}
