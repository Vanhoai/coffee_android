package com.example.coffee.screens.bottom.Gift;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecyclePromoAdapter;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.Mission;

import java.util.ArrayList;

public class GiftFragment extends Fragment {
    private RecyclerView recyclerMission;
    private ArrayList<Product> products;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.gift_fragment, container, false);

        recyclerMission = view.findViewById(R.id.recyclerMission);

        products = new ArrayList<>();

        ArrayList<Mission> missions = new ArrayList<>();
        renderPromo(recyclerMission, missions);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void renderPromo(RecyclerView recyclerView, ArrayList<Mission> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclePromoAdapter adapter = new RecyclePromoAdapter(getContext(),data);
        recyclerView.setAdapter(adapter);
    }
}
