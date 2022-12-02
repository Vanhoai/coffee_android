package com.example.coffee.screens.bottom.Gift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleAllShopAdapter;
import com.example.coffee.callbacks.ShopCallback;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.models.Shop.ShopResponse;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.screens.bottom.Shop.PlaceListActivity;
import com.example.coffee.services.ShopService;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;

public class ListMissionActivity extends AppCompatActivity {

    private TextView tvTitle, tvListMission;
    private ImageView backNavigation;
    private RecyclerView recycleListMission;
    private ScrollView svListMission;
    private ArrayList<Shop> shops ;
    private ShopService shopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mission);

        tvListMission = findViewById(R.id.tvListMission);
        svListMission = findViewById(R.id.svListMission);

        initView();

        shops = new ArrayList<>();

        shopService = new ShopService();

        initShop();

        handleOnClick();

        setView();
    }

    private void initView(){
        tvTitle = findViewById(R.id.tvTitle);
        backNavigation = findViewById(R.id.backNavigation);
        recycleListMission = findViewById(R.id.recycleListMission);
    }

    private void setView(){
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String title = bundle.getString("Page Title", "ListMission");
            tvTitle.setText(title);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void handleOnClick(){
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMissionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void initShop(){
        shopService.getAllShop(new ShopCallback() {
            @Override
            public void onSuccess(boolean value, ShopResponse shopResponse) {
                Logger.log("SHOP RESPONSE", shopResponse);

                if(shopResponse.getShops().size() == 0){
                    svListMission.setVisibility(View.GONE);
                    tvListMission.setVisibility(View.VISIBLE);
                }else{
                    svListMission.setVisibility(View.VISIBLE);
                    tvListMission.setVisibility(View.GONE);
                    shops.addAll(shopResponse.getShops());
                    renderShop(recycleListMission, shops);
                }
            }

            @Override
            public void onFailed(boolean value) {
                Logger.log("SHOP-RESPONSE", "ERROR");
            }
        });
    }

    public void renderShop(RecyclerView recyclerView, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListMissionActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleAllShopAdapter adapter = new RecycleAllShopAdapter(ListMissionActivity.this,data);
        recyclerView.setAdapter(adapter);
    }
}