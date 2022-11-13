package com.example.coffee.screens.bottom.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleProductDetailAdapter;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.screens.bottom.Shop.DetailPlaceActivity;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {

    AppCompatButton btnContinuePayment;
    ImageView backNavigation;
    ArrayList<Product> products;
    RecyclerView recycleProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        // mapping
        btnContinuePayment = findViewById(R.id.btnContinuePayment);
        backNavigation = findViewById(R.id.backNavigation);
        recycleProducts = findViewById(R.id.recycleProducts);

        // init shared data
        products = new ArrayList<>();

        // get data
        getData();

        // set view
        render(products);


        // handle logic
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this, DetailPlaceActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnContinuePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this, PaymentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            products = (ArrayList<Product>) bundle.getSerializable("products");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void render(ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckOutActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycleProducts.setLayoutManager(linearLayoutManager);
        RecycleProductDetailAdapter adapter = new RecycleProductDetailAdapter(CheckOutActivity.this, data);
        recycleProducts.setAdapter(adapter);
    }
}