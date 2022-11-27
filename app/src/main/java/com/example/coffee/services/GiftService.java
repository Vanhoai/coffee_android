package com.example.coffee.services;

import static com.example.coffee.interfaces.GiftInterfaceAPI.GIFT_URL;

import com.example.coffee.callbacks.GiftCallback;
import com.example.coffee.interfaces.GiftInterfaceAPI;
import com.example.coffee.models.Shop.GiftResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GiftService {

 private GiftInterfaceAPI API;

 public GiftInterfaceAPI getAPI(){
     return API;
 }

 public void GiftInterfaceAPI(){
     API = new Retrofit.Builder()
             .baseUrl(GIFT_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(GiftInterfaceAPI.class);
 }

 public void getGift(int limit, int id, GiftCallback callback){
     try {
         getAPI().getGift(limit, id).enqueue(new Callback<GiftResponse>() {
             @Override
             public void onResponse(Call<GiftResponse> call, Response<GiftResponse> response) {
                 if (response.code() == 200){
                     callback.onSuccess(true, response.body());
                 }else {
                     callback.onFailed(false);
                 }

             }

             @Override
             public void onFailure(Call<GiftResponse> call, Throwable t) {
                callback.onFailed(false);
             }
         });
     }catch (Exception e){
         e.printStackTrace();
     }
 }
}
