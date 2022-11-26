package com.example.coffee.screens.bottom.Shop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tvTotal;
    TextView tvDistrict;
    AppCompatButton btnOrderNow;
    RecyclerView recycleProducts;
    ArrayList<Product> products;
    int shopId;
    ShopService shopService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        // init view
        initView();

        // init shared data
        products = new ArrayList<>();
        shopService = new ShopService();

        // set view
        render(products);

        // handle click
        handleClick();

        // set view
        detailPlace();
    }

    public void handleClick() {
        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Product> productCurrent = new ArrayList<>();
                for (Product product : products){
                    if (product.getCurrent() > 0){
                        productCurrent.add(product);
                    }
                }
                if (productCurrent.size() <= 0) {
                    Toast.makeText(DetailPlaceActivity.this, "NO PRODUCT SELECTED", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(DetailPlaceActivity.this, CheckOutActivity.class);
                Bundle bundle1 = new Bundle();
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

    public void initView() {
        btnOrderNow = findViewById(R.id.btnOrderNow);
        recycleProducts = findViewById(R.id.recycleProducts);
        backNavigation = findViewById(R.id.backNavigation);
        imageDetailPlace =findViewById(R.id.imageDetailPlace);
        tvNameDetailPlace = findViewById(R.id.tvNameDetailPlace);
        tvDistrict = findViewById(R.id.tvDistrict);
        tvTotal = findViewById(R.id.tvTotal);
    }

    public void detailPlace(){
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            shopId = bundle.getInt("id",-1);

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
        }catch (Exception e){
            e.printStackTrace();
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
        RecycleProductDetailAdapter adapter = new RecycleProductDetailAdapter(DetailPlaceActivity.this, data, new RecycleProductDetailAdapter.UpdateTotal() {
            @Override
            public void update(ArrayList<Product> products) {
                float total = 0;
                for (int i = 0; i < products.size(); i++) {
                    total += products.get(i).getPrice() * products.get(i).getCurrent();
                }

                tvTotal.setText(String.format("%.1f VND", total));
            }
        });
        recycleProducts.setAdapter(adapter);
    }
}