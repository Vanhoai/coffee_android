package com.example.coffee.screens.bottom.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.adapters.RecycleProductDetailAdapter;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.screens.bottom.Product.CheckOutActivity;

import java.util.ArrayList;

public class DetailPlaceActivity extends AppCompatActivity {

    ImageView backNavigation;
    AppCompatButton btnOrderNow;
    RecyclerView recycleProducts;
    ArrayList<Product> products;
    Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        // mapping
        btnOrderNow = findViewById(R.id.btnOrderNow);
        recycleProducts = findViewById(R.id.recycleProducts);
        backNavigation = findViewById(R.id.backNavigation);

        // init shared data
        products = new ArrayList<>();

        // get data
        getData();

        // set view
        render(products);

        // handle logic
        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPlaceActivity.this, CheckOutActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("products", products);
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();
            }
        });

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DetailPlaceActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    public void getData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            shop = (Shop) bundle.getSerializable("shop");
            products.addAll(shop.getProducts());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void render(ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailPlaceActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycleProducts.setLayoutManager(linearLayoutManager);
        RecycleProductDetailAdapter adapter = new RecycleProductDetailAdapter(DetailPlaceActivity.this, data);
        recycleProducts.setAdapter(adapter);
    }
}