package com.example.coffee.screens.bottom.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleProductDetailAdapter;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.screens.bottom.Shop.DetailPlaceActivity;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {

    AppCompatButton btnContinuePayment;
    ImageView backNavigation;
    ArrayList<Product> products;
    RecyclerView recycleProducts;
    TextView tvTotal, tvAmount, tvShip, tvPromo;
    int shopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        // init view
        initView();


        // init shared data
        products = new ArrayList<>();

        // get data
        getData();

        // set view
        render(products);

        getBundle();


        // handle logic
        handleOnclick();
    }

    public void handleOnclick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this, DetailPlaceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", shopId);
                intent.putExtras(bundle);
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

    public void initView() {
        btnContinuePayment = findViewById(R.id.btnContinuePayment);
        backNavigation = findViewById(R.id.backNavigation);
        recycleProducts = findViewById(R.id.recycleProducts);
        tvAmount = findViewById(R.id.tvAmount);
        tvPromo = findViewById(R.id.tvPromo);
        tvShip = findViewById(R.id.tvShip);
        tvTotal = findViewById(R.id.tvTotal);
    }

    public void getBundle() {
       try {
           Intent intentStart = getIntent();
           Bundle bundle = intentStart.getExtras();
           shopId = bundle.getInt("id", -1);
       } catch (Exception exception){
           exception.printStackTrace();
       }
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
        RecycleProductDetailAdapter adapter = new RecycleProductDetailAdapter(CheckOutActivity.this, data, new RecycleProductDetailAdapter.UpdateTotal() {
            @Override
            public void update(ArrayList<Product> products) {
                float total = 0;
                for (int i = 0; i < products.size(); i++) {
                    total += products.get(i).getPrice() * products.get(i).getCurrent();
                }

                tvAmount.setText(String.format("%.0f", total));
            }
        });
        recycleProducts.setAdapter(adapter);
    }
}