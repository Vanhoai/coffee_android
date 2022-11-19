package com.example.coffee.screens.bottom.Shop;

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
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.screens.bottom.MainActivity;

import java.util.ArrayList;

public class PlaceListActivity extends AppCompatActivity {

    ImageView backNavigation;
    RecyclerView recyclePlaceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        recyclePlaceList = findViewById(R.id.recyclePlaceList);
        backNavigation = findViewById(R.id.backNavigation);


        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceListActivity.this, MainActivity.class);
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


        renderProduct(recyclePlaceList, products);


    }

    public void renderProduct(RecyclerView recyclerView, ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlaceListActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleProductAdapter adapter = new RecycleProductAdapter(PlaceListActivity.this,data);
        recyclerView.setAdapter(adapter);
    }
    }

