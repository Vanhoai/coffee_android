package com.example.coffee.screens.bottom.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.Mission;
import com.example.coffee.screens.bottom.Home.PromoActivity;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView recyclerProductList;
    ImageView backNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        backNavigation = findViewById(R.id.backNavigation);
        recyclerProductList = findViewById(R.id.recycleProductsList);


        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayList<Product> products = new ArrayList<>();

        renderProduct(recyclerProductList, products);
    }

    public void renderProduct(RecyclerView recyclerView, ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductListActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleProductAdapter adapter = new RecycleProductAdapter(ProductListActivity.this, data);
        recyclerView.setAdapter(adapter);
    }
}