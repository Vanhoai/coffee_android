package com.example.coffee.screens.bottom.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecyclePromoAdapter;
import com.example.coffee.models.Shop.Mission;
import com.example.coffee.screens.bottom.MainActivity;

import java.util.ArrayList;

public class PromoActivity extends AppCompatActivity {
    ImageView backNavigation;
    RecyclerView recyclePromo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        backNavigation = findViewById(R.id.backNavigation);
        recyclePromo = findViewById(R.id.recyclePromo);

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayList<Mission> missions = new ArrayList<>();
        renderPromo(recyclePromo,missions);
    }

    public void renderPromo(RecyclerView recyclerView, ArrayList<Mission> data) {
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