package com.example.coffee.screens.bottom.Shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.AttributeSet;
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
import com.example.coffee.adapters.RecycleViewAllShopAdapter;
import com.example.coffee.adapters.RecycleViewHistoryAdapter;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Product.Shop.Shop;

import java.util.ArrayList;

public class ShopFragment extends Fragment {
    private RecyclerView recyclerViewNearbyPlace;
    private RecyclerView recyclerViewAllShop;
    private ArrayList<Shop> list;
    private ArrayList<Product> products;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.shop_fragment, container, false);
        recyclerViewNearbyPlace = view.findViewById(R.id.recycleViewNearbyPlace);
        recyclerViewAllShop = view.findViewById(R.id.recycleViewAllShop);

        list = new ArrayList<>();
        products = new ArrayList<>();

        //init data
        list.add(new Shop(1, "Tân Bình District", "Lorem Ipsum has been the industry standard dummy text ever since ...", "", products, 1, 1));
        list.add(new Shop(1, "Tân Bình District", "Lorem Ipsum has been the industry standard dummy text ever since ...", "", products, 1, 1));
        list.add(new Shop(1, "Tân Bình District", "Lorem Ipsum has been the industry standard dummy text ever since ...", "", products, 1, 1));
        list.add(new Shop(1, "Tân Bình District", "Lorem Ipsum has been the industry standard dummy text ever since ...", "", products, 1, 1));
        list.add(new Shop(1, "Tân Bình District", "Lorem Ipsum has been the industry standard dummy text ever since ...", "", products, 1, 1));
        list.add(new Shop(1, "Tân Bình District", "Lorem Ipsum has been the industry standard dummy text ever since ...", "", products, 1, 1));
        list.add(new Shop(1, "Tân Bình District", "Lorem Ipsum has been the industry standard dummy text ever since ...", "", products, 1, 1));

        //render
        renderPlace(recyclerViewNearbyPlace, list);
        renderAllShop(recyclerViewAllShop, list);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void renderPlace(RecyclerView recyclerViewNearbyPlace, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewNearbyPlace.setLayoutManager(linearLayoutManager);
        RecycleNearlyAdapter adapter = new RecycleNearlyAdapter(getContext(), data);
        recyclerViewNearbyPlace.setAdapter(adapter);
    }

    public void renderAllShop(RecyclerView recyclerViewAllShop, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewAllShop.setLayoutManager(linearLayoutManager);
        RecycleViewAllShopAdapter adapter = new RecycleViewAllShopAdapter(getContext(), data);
        recyclerViewAllShop.setAdapter(adapter);
    }
}
