package com.example.coffee.screens.bottom.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.models.Product.Comment;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Product.Shop.Shop;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.screens.bottom.Shop.PlaceListActivity;

import java.util.ArrayList;

public class PromoActivity extends AppCompatActivity {
    ImageView backNavigation;
    RecyclerView recyclePromo;
    RecyclerView recyclePromo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        backNavigation = findViewById(R.id.backNavigation);
        recyclePromo = findViewById(R.id.recyclePromo);
        recyclePromo2 =findViewById(R.id.recyclePromo2);

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());
        comments.add(new Comment());
        comments.add(new Comment());

        products.add(new Product(1, "The Coffee Storm", "", 25000, 100, 100, comments));
        products.add(new Product(1, "The Coffee Storm", "", 25000, 100, 100, comments));
        products.add(new Product(1, "The Coffee Storm", "", 25000, 100, 100, comments));
        products.add(new Product(1, "The Coffee Storm", "", 25000, 100, 100, comments));
        products.add(new Product(1, "The Coffee Storm", "", 25000, 100, 100, comments));

        ArrayList<Shop> shops = new ArrayList<>();
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", products, 1, 1));
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", products, 1, 1));
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", products, 1, 1));
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", products, 1, 1));
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", products, 1, 1));


        renderProduct(recyclePromo, products);


    }

    public void renderProduct(RecyclerView recyclerView, ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PromoActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleProductAdapter adapter = new RecycleProductAdapter(PromoActivity.this,data);
        recyclerView.setAdapter(adapter);

    }
}