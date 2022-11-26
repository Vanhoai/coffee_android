package com.example.coffee.screens.bottom.Product;

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
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.callbacks.ProductCallback;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Product.ProductResponse;

import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.services.ProductService;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView recyclerProductList;
    ImageView backNavigation;
    ArrayList<Product> products;
    ProductService productService;
    TextView tvTitle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //mapping
        backNavigation = findViewById(R.id.backNavigation);
        recyclerProductList = findViewById(R.id.recycleProductsList);
        tvTitle = findViewById(R.id.tvTitle);

        //init data
        products = new ArrayList<>();
        productService = new ProductService();

        // call api
        initProduct();

        // handle onclick
        handleOnclick();

        // set view
        setView();
    }

    private void setView() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String title = bundle.getString("Page Title", "All Product");
            tvTitle.setText(title);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void handleOnclick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void initProduct(){
        productService.getAllProduct(new ProductCallback() {
            @Override
            public void onSuccess(boolean value, ProductResponse productResponse) {
                Logger.log("PRODUCTS", productResponse);

                products.addAll(productResponse.getProducts());
                renderProduct(recyclerProductList, products);
            }

            @Override
            public void onFailed(boolean value) {
                Logger.log("PRODUCTS", "ERROR");
            }
        });
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