package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.Product.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductInterfaceAPI {

    String PRODUCT_URL = String.format("%s/products/", BASE_URL);

    @GET("all")
    Call<ProductResponse> getAllProduct();

    @GET("?")
    Call<ProductResponse> getProduct(
        @Query("limit") int limit,
        @Query("skip") int skip,
        @Query("sort") String sort
    );
}
