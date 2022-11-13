package com.example.coffee.screens.bottom.Home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleNearlyAdapter;
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.models.Product.Comment;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.screens.bottom.Profile.HistoryActivity;
import com.example.coffee.screens.bottom.Profile.TopUpActivity;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    RecyclerView recycleViewNearbyPlace;
    RecyclerView recycleViewBestSeller;
    ImageView imageTopUp;
    ImageView imagePay;
    ImageView imagePromo;
    ImageView imageHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.home_fragment, container, false);

        // mapping
        recycleViewNearbyPlace = view.findViewById(R.id.recycleViewNearbyPlace);
        recycleViewBestSeller = view.findViewById(R.id.recycleViewBestSeller);
        imageTopUp = view.findViewById(R.id.imageTopUp);
        imagePay = view.findViewById(R.id.imagePay);
        imagePromo = view.findViewById(R.id.imagePromo);
        imageHistory = view.findViewById(R.id.imageHistory);


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

        renderPlace(recycleViewNearbyPlace, shops);
        renderProduct(recycleViewBestSeller, products);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imageTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TopUpActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        imagePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imagePromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
    }

    public void renderPlace(RecyclerView recyclerView, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleNearlyAdapter adapter = new RecycleNearlyAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
    }

    public void renderProduct(RecyclerView recyclerView, ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleProductAdapter adapter = new RecycleProductAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
    }

}
