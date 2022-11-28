package com.example.coffee.screens.bottom.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecyclePromoAdapter;
import com.example.coffee.callbacks.PromoCallback;
import com.example.coffee.models.Shop.Mission;
import com.example.coffee.models.Shop.Promo;
import com.example.coffee.models.Shop.PromoResponse;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.services.GiftService;
import com.example.coffee.utils.HelperFunction;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

import java.util.ArrayList;

public class PromoActivity extends AppCompatActivity {

     ImageView backNavigation;
     RecyclerView recyclePromo;
     ArrayList<Mission> missions;
     GiftService giftService;
     LinearLayout CardHottest;
     ImageView imageHottest;
     TextView tvNameHottest;
     TextView tvDescription;
     TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        // init view
        initView();

        // handle click
        handleClick();

        // init data
        missions = new ArrayList<>();
        giftService = new GiftService();

        // init mission
        initPromo();
    }


    private void handleClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        backNavigation = findViewById(R.id.backNavigation);
        recyclePromo = findViewById(R.id.recyclePromo);
        CardHottest = findViewById(R.id.CardHottest);
        tvDescription =findViewById(R.id.tvDescription);
        tvNameHottest = findViewById(R.id.tvNameHottest);
        imageHottest = findViewById(R.id.imageHottest);
        tvCount = findViewById(R.id.tvCount);

    }

    private void initPromo(){
        try {

            giftService.getPromo(5, new PromoCallback() {
                @Override
                public void onSuccess(boolean value, PromoResponse promoResponse) {
                    Logger.log("PROMO", promoResponse);
                    missions.addAll(promoResponse.getPromo().getMissions());
                    renderPromo(recyclePromo, missions);

                    if (promoResponse.getPromo().getHottest() == null){
                        CardHottest.setVisibility(View.GONE);
                    }else {
                       Mission mission = promoResponse.getPromo().getHottest();
                       imageHottest.setImageResource(HelperFunction.getDrawable(mission.getType().getPercent()));
                       tvNameHottest.setText(mission.getName());
                       tvDescription.setText(mission.getDescription());
                       tvCount.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailed(boolean value) {
                    Logger.log("PROMO", "ERROR");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void renderPromo(RecyclerView recyclerView, ArrayList<Mission> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PromoActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclePromoAdapter adapter = new RecyclePromoAdapter(PromoActivity.this,data);
        recyclerView.setAdapter(adapter);
    }
}