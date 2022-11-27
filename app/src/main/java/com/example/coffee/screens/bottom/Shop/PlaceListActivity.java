package com.example.coffee.screens.bottom.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleAllShopAdapter;
import com.example.coffee.adapters.RecycleNearlyAdapter;
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.callbacks.ProductCallback;
import com.example.coffee.callbacks.ShopCallback;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Product.ProductResponse;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.models.Shop.ShopResponse;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.services.ProductService;
import com.example.coffee.services.ShopService;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;

public class PlaceListActivity extends AppCompatActivity {

    TextView tvTitle;
    ImageView backNavigation;
    RecyclerView recyclePlaceList;
    ArrayList<Shop> shops ;
    ShopService shopService;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        //mapping
        recyclePlaceList = findViewById(R.id.recyclePlaceList);
        backNavigation = findViewById(R.id.backNavigation);
        tvTitle = findViewById(R.id.tvTitle);

        //init data
        shops = new ArrayList<>();
        shopService = new ShopService();

        // call api
        initShop();

        // handle onclick
        handleOnClick();

        // set view
        setView();
    }

    private void setView() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String title = bundle.getString("Page Title", "All Shop");
            tvTitle.setText(title);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void handleOnClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void initShop(){
        shopService.getAllShop(new ShopCallback() {
            @Override
            public void onSuccess(boolean value, ShopResponse shopResponse) {
                Logger.log("SHOPRESPONSE", shopResponse);

                shops.addAll(shopResponse.getShops());
                renderShop(recyclePlaceList, shops);
            }

            @Override
            public void onFailed(boolean value) {
                Logger.log("SHOP-RESPONSE", "ERROR");
            }
        });
    }

    public void renderShop(RecyclerView recyclerView, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlaceListActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleAllShopAdapter adapter = new RecycleAllShopAdapter(PlaceListActivity.this,data);
        recyclerView.setAdapter(adapter);
    }
}

