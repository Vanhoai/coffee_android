package com.example.coffee.screens.bottom.Product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.adapters.RecycleCommentAdapter;
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.adapters.RecycleProductDetailAdapter;
import com.example.coffee.callbacks.ProductDetailCallback;
import com.example.coffee.models.Order.Gift;
import com.example.coffee.models.Order.Type;
import com.example.coffee.models.Product.Comment;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Product.ProductDetail;
import com.example.coffee.models.Product.ProductDetailResponse;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.screens.bottom.Shop.DetailPlaceActivity;
import com.example.coffee.services.ProductService;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerComment;
    private ImageView imageProduct, bookmark, backNavigation;
    private TextView tvNameProduct, tvPriceProduct, tvExploredProduct, tvDescription;
    private ArrayList<Comment> comments;
    private ProductService productService;
    private AppCompatButton btnReview, btnTextProduct;
    private boolean check = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // init
        initView();

        // click
        handleOnClick();

        // init shared data
        comments = new ArrayList<>();

        // init service
        productService = new ProductService();

        // call api
        detailProduct();
    }

    public void initView() {
        recyclerComment = findViewById(R.id.recycleComment);
        imageProduct = findViewById(R.id.imageDetailProduct);
        tvNameProduct = findViewById(R.id.tvNameProduct);
        tvPriceProduct = findViewById(R.id.tvPriceProduct);
        tvExploredProduct = findViewById(R.id.tvExplored);
        tvDescription = findViewById(R.id.tvDescriptionProduct);
        btnTextProduct = findViewById(R.id.btnTextProduct);
        backNavigation = findViewById(R.id.backNavigation);
        btnReview = findViewById(R.id.btnReview);
        bookmark = findViewById(R.id.bookmark);
    }

    public void handleOnClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogReview();
            }
        });
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = !check;
                if (check) {
                    bookmark.setImageResource(R.drawable.gift_active);
                } else {
                    bookmark.setImageResource(R.drawable.gift);
                }
            }
        });
    }

    public void detailProduct() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            int id = bundle.getInt("id", -1);

            productService.getProductDetail(id, new ProductDetailCallback() {
                @Override
                public void onSuccess(boolean value, ProductDetailResponse productResponse) {
                    Logger.log("PRODUCT RESPONSE", productResponse);
                    tvNameProduct.setText(productResponse.getProductDetail().getName());
                    tvPriceProduct.setText(String.format("%.2f VND", productResponse.getProductDetail().getPrice()));
                    tvExploredProduct.setText(String.format("%d people have explored", productResponse.getProductDetail().getExplored()));
                    tvDescription.setText(productResponse.getProductDetail().getDescription());
                    btnTextProduct.setText(String.valueOf(productResponse.getProductDetail().getComments().size()));
                    Glide.with(ProductDetailActivity.this).load(productResponse.getProductDetail().getImage()).into(imageProduct);
                    comments.addAll(productResponse.getProductDetail().getComments());
                    render(comments);
                }

                @Override
                public void onFailed(boolean value) {
                    Logger.log("ERROR", "ERROR RESPONSE");
                    render(comments);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dialogReview() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetailActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_review, null);
        builder.setView(view);

        Spinner spinnerReview = findViewById(R.id.spinnerReview);
        setViewSpinner(spinnerReview);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setViewSpinner(Spinner spinnerReview) {
        ArrayList<HashMap<String, Integer>> result = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put("star", i);
            result.add(hashMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                ProductDetailActivity.this,
                result,
                android.R.layout.simple_list_item_1,
                new String[]{"code"},
                new int[]{android.R.id.text1}
        );
        spinnerReview.setAdapter(simpleAdapter);

        spinnerReview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void render(ArrayList<Comment> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailActivity.this);
        recyclerComment.setLayoutManager(linearLayoutManager);
        RecycleCommentAdapter adapter = new RecycleCommentAdapter(data, ProductDetailActivity.this);
        recyclerComment.setAdapter(adapter);
    }

}