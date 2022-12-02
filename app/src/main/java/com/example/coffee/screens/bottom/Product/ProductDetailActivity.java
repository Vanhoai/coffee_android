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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.adapters.RecycleCommentAdapter;
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.adapters.RecycleProductDetailAdapter;
import com.example.coffee.callbacks.CommentCallback;
import com.example.coffee.callbacks.ProductDetailCallback;
import com.example.coffee.models.Order.Order;
import com.example.coffee.models.Product.Comment;
import com.example.coffee.models.Product.CommentResponse;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Product.ProductDetail;
import com.example.coffee.models.Product.ProductDetailResponse;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.screens.bottom.Shop.DetailPlaceActivity;
import com.example.coffee.services.CommentService;
import com.example.coffee.services.ProductService;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerComment;
    private ImageView imageProduct, bookmark, backNavigation;
    private TextView tvNameProduct, tvPriceProduct, tvExploredProduct, tvDescription;
    private ArrayList<Comment> comments;
    private ProductService productService;
    private AppCompatButton btnReview, btnTextProduct;
    private boolean check = false;
    AppCompatButton btnSend, btnCancel;
    RatingBar ratingComment;
    EditText edComment;

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
                Intent intent1 = null;

                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                String status = bundle.getString("status", "HOME");
                if (status.equals("HOME")) {
                    intent1 = new Intent(ProductDetailActivity.this, MainActivity.class);
                } else if (status.equals("PLACE")) {
                    intent1 = new Intent(ProductDetailActivity.this, DetailPlaceActivity.class);
                    int shop = bundle.getInt("shop", 1);
                    Order order = (Order) bundle.getSerializable("OrderDetail");
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("id", shop);
                    bundle1.putSerializable("OrderDetail", order);
                    intent1.putExtras(bundle1);
                }
                
                startActivity(intent1);
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
                    comments.clear();
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

    @SuppressLint("MissingInflatedId")
    public void dialogReview() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetailActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_review, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        //mapping
        ratingComment = view.findViewById(R.id.ratingCommnet);
        edComment = view.findViewById(R.id.edCommnent);
        btnSend = view.findViewById(R.id.btnSend);
        btnCancel = view.findViewById(R.id.btnCancel);

        CommentService commentService = new CommentService();
        //ratingComment.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
        // @Override
        // public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        // Logger.log("RATING", v);
        // }
        // });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = UserInformation.getUser(ProductDetailActivity.this);
                int idUser = user.getId();

                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                int id = bundle.getInt("id", -1);

                String commnent = edComment.getText().toString();
                int rating = (int) ratingComment.getRating();

                Logger.log("ID USER", idUser);
                Logger.log("ID", id);
                Logger.log("COMMENT", commnent);
                Logger.log("RATING", rating);

                commentService.getComment(idUser, id, commnent, rating, new CommentCallback() {
                    @Override
                    public void onSuccess(boolean value, CommentResponse commentResponse) {
                        Logger.log("COMMENT", commentResponse);
                        detailProduct();
                        dialog.dismiss();

                    }

                    @Override
                    public void onFailed(boolean value) {

                    }
                });


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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