package com.example.coffee.screens.bottom.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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

    private RecyclerView recyclerProductList;
    private ImageView backNavigation;
    private ArrayList<Product> products;
    private ProductService productService;
    private TextView tvTitle, tvNothing;
    private ScrollView svMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // init view
        backNavigation = findViewById(R.id.backNavigation);
        recyclerProductList = findViewById(R.id.recycleProductsList);
        tvTitle = findViewById(R.id.tvTitle);
        tvNothing = findViewById(R.id.tvNothing);
        svMain = findViewById(R.id.svMain);

        // init data
        products = new ArrayList<>();

        // init service
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

    public void initView(){
        backNavigation = findViewById(R.id.backNavigation);
        recyclerProductList = findViewById(R.id.recycleProductsList);
        tvTitle = findViewById(R.id.tvTitle);
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

                if(productResponse.getProducts().size() == 0){
                    svMain.setVisibility(View.GONE);
                    tvNothing.setVisibility(View.VISIBLE);
                }else{
                    svMain.setVisibility(View.VISIBLE);
                    tvNothing.setVisibility(View.GONE);
                    products.addAll(productResponse.getProducts());
                    renderProduct(recyclerProductList, products);
                }

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
        RecycleProductAdapter adapter = new RecycleProductAdapter(ProductListActivity.this, data, "LIST");
        recyclerView.setAdapter(adapter);
    }
}