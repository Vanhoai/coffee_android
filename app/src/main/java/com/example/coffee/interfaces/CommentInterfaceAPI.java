package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.Product.CommentResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CommentInterfaceAPI {
    String COMMENT_URL = String.format("%s/comment/", BASE_URL);
    // http://192.168.1.108:8080/api/v1/comment/
    @POST("?")
    @FormUrlEncoded()
    Call<CommentResponse> getComment(
        @Field("userId") int userId,
        @Field("productId") int productId,
        @Field("content") String content,
        @Field("rating") int rating
    );
}
