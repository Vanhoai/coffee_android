package com.example.coffee.services;

import static com.example.coffee.interfaces.ProductInterfaceAPI.PRODUCT_URL;
import static com.example.coffee.interfaces.ShopInterfaceAPI.SHOP_URL;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.ProductCallback;
import com.example.coffee.interfaces.ProductInterfaceAPI;
import com.example.coffee.interfaces.ShopInterfaceAPI;
import com.example.coffee.models.Product.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductService {

    private  ProductInterfaceAPI API;

    public synchronized ProductInterfaceAPI getAPI() {return API;}

    public ProductService() {
        API = new Retrofit.Builder()
                .baseUrl(PRODUCT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductInterfaceAPI.class);
    }

    public void getAllProduct(ProductCallback callback) {
        try {
            getAPI().getAllProduct().enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(@NonNull Call<ProductResponse> call, @NonNull Response<ProductResponse> response) {
                    if (response.code() == 200) {
                        callback.onSuccess(true, response.body());
                    } else {
                        callback.onFailed(false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ProductResponse> call, @NonNull Throwable throwable) {
                    callback.onFailed(false);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
