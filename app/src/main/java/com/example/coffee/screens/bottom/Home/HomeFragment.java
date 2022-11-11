package com.example.coffee.screens.bottom.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recycleViewNearbyPlace, recycleViewBestSeller;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.home_fragment, container, false);
        recycleViewNearbyPlace = view.findViewById(R.id.recycleViewNearbyPlace);
        recycleViewBestSeller = view.findViewById(R.id.recycleViewBestSeller);

        ArrayList<Shop> shops = new ArrayList<>();
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", 1, 1));
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", 1, 1));
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", 1, 1));
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", 1, 1));
        shops.add(new Shop(1, "Tân Bình Ditrict", "", "", 1, 1));

        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());
        comments.add(new Comment());
        comments.add(new Comment());

        products.add(new Product(1, "", "", 100, 100, 100, comments));
        products.add(new Product(1, "", "", 100, 100, 100, comments));
        products.add(new Product(1, "", "", 100, 100, 100, comments));
        products.add(new Product(1, "", "", 100, 100, 100, comments));
        products.add(new Product(1, "", "", 100, 100, 100, comments));

        renderPlace(recycleViewNearbyPlace, shops);
        renderProduct(recycleViewBestSeller, products);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void renderPlace(RecyclerView recyclerView, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleNearlyAdapter adapter = new RecycleNearlyAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
    }

    public void renderProduct(RecyclerView recyclerView, ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleProductAdapter adapter = new RecycleProductAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
    }

}
