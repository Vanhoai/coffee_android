package com.example.coffee.interfaces;

import static com.example.coffee.interfaces.BaseAPI.BASE_URL;

import com.example.coffee.models.User.HistoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HistoryInterfaceAPI {
    String HISTORY_URL = String.format("%s/histories/", BASE_URL);

    @GET("user/{id}")
    Call<HistoryResponse> getAllHistoryOfUser(
            @Path("id") int id
    );
}
