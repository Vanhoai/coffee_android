package com.example.coffee.services;

import static com.example.coffee.interfaces.CommentInterfaceAPI.COMMENT_URL;
import static com.example.coffee.interfaces.GiftInterfaceAPI.GIFT_URL;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.CommentCallback;
import com.example.coffee.interfaces.CommentInterfaceAPI;
import com.example.coffee.interfaces.GiftInterfaceAPI;
import com.example.coffee.models.Product.CommentResponse;
import com.example.coffee.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentService {

    private final CommentInterfaceAPI API;

    public synchronized CommentInterfaceAPI getAPI() {
        return API;
    }

    public CommentService() {
        API = new Retrofit.Builder()
                .baseUrl(COMMENT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CommentInterfaceAPI.class);
    }

    public void getComment(int userId, int productId, String content, int rating, CommentCallback callback){
        try {
            getAPI().getComment(userId, productId, content, rating).enqueue(new Callback<CommentResponse>() {
                @Override
                public void onResponse(@NonNull Call<CommentResponse> call, @NonNull Response<CommentResponse> response) {
                    if (response.code() == 200){
                        Logger.log("COMMENT", response);
                        callback.onSuccess(true, response.body());
                    }else {
                        callback.onFailed(false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<CommentResponse> call, @NonNull Throwable t) {
                        callback.onFailed(false);
                        Logger.log("COMMENT", "ERROR");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
