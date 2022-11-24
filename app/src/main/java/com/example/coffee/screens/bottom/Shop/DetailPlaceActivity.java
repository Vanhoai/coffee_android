package com.example.coffee.screens.bottom.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.adapters.RecycleProductDetailAdapter;
import com.example.coffee.callbacks.ShopDetailCallback;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.ProductDetail;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.models.Shop.ShopDetailResponse;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.screens.bottom.Product.CheckOutActivity;
import com.example.coffee.services.ShopService;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;

public class DetailPlaceActivity extends AppCompatActivity {

    ImageView backNavigation;
    ImageView imageDetailPlace;
    TextView tvNameDetailPlace;
    TextView tvDistrict;
    AppCompatButton btnOrderNow;
    RecyclerView recycleProducts;
    ArrayList<Product> products;
    int shopId;
    ShopService shopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        // mapping
        btnOrderNow = findViewById(R.id.btnOrderNow);
        recycleProducts = findViewById(R.id.recycleProducts);
        backNavigation = findViewById(R.id.backNavigation);
        imageDetailPlace =findViewById(R.id.imageDetailPlace);
        tvNameDetailPlace = findViewById(R.id.tvNameDetailPlace);
        tvDistrict = findViewById(R.id.tvDistrict);

        // init shared data
        products = new ArrayList<>();
        shopService = new ShopService();

        detailPlace();

        // set view
        render(products);


        // handle logic
        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPlaceActivity.this, CheckOutActivity.class);
                Bundle bundle1 = new Bundle();
                ArrayList<Product> productCurrent = new ArrayList<>();
                for (Product product : products){
                    if (product.getCurrent() > 0){
                        productCurrent.add(product);
                    }
                }
                bundle1.putSerializable("products", productCurrent);
                bundle1.putInt("id", shopId);
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

    public void detailPlace(){
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            shopId = bundle.getInt("id",-1);
        }catch (Exception e){
            e.printStackTrace();
        }
        shopService.getDetail(shopId, new ShopDetailCallback() {
            @Override
            public void onSuccess(boolean value, ShopDetailResponse shopDetailResponse) {
                Logger.log("RESPONSE", shopDetailResponse);
                tvNameDetailPlace.setText("Aurora Coffee");
                tvDistrict.setText(shopDetailResponse.getShopDetail().getLocation());
                Glide.with(DetailPlaceActivity.this).load(shopDetailResponse.getShopDetail().getImage()).into(imageDetailPlace);
                for (ProductDetail productDetail : shopDetailResponse.getShopDetail().getProducts()){
                    products.add(productDetail.getProduct());
                }
                render(products);
            }

            @Override
            public void onFailed(boolean value) {

            }
        });

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