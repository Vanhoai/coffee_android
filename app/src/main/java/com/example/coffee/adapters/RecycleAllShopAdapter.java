package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.screens.bottom.Shop.DetailPlaceActivity;
=======
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.models.Shop.Shop;
>>>>>>> f502e4709d88377758c07595d06b704c6dde1d84

import java.util.ArrayList;

public class RecycleAllShopAdapter extends RecyclerView.Adapter<RecycleAllShopAdapter.AllShopViewHolder> {
<<<<<<< HEAD
    private final Context context;
    private final ArrayList<Shop> shops;

    public RecycleAllShopAdapter(Context context, ArrayList<Shop> shops) {
        this.context = context;
        this.shops = shops;
=======
    private Context context;
    private ArrayList<Shop> list_shop;

    public RecycleAllShopAdapter(Context context, ArrayList<Shop> list_shop) {
        this.context = context;
        this.list_shop = list_shop;
>>>>>>> f502e4709d88377758c07595d06b704c6dde1d84
    }

    @NonNull
    @Override
    public AllShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_allshop, parent, false);
        return new AllShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllShopViewHolder holder, int position) {
<<<<<<< HEAD
        Shop shop = shops.get(position);
        holder.tvNameShop.setText("Aurora Coffee");
        String[] desc = shop.getDescription().split(" ");
        if (desc.length > 16) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                result.append(desc[i]);
                if (i < 15) {
                    result.append(" ");
                }
            }
            holder.tvDescriptionShop.setText(String.format("%s ...", result));
        } else {
            holder.tvDescriptionShop.setText(shop.getDescription());
        }
        Glide.with(context).load(shop.getImage()).into(holder.imageAllShop);
        holder.cardViewAllShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPlaceActivity.class);
                context.startActivity(intent);
            }
        });
=======
        Shop shop = list_shop.get(position);
        holder.tvNameShop.setText("Aurora Coffee");
        holder.tvDescriptionShop.setText(shop.getDescription());
>>>>>>> f502e4709d88377758c07595d06b704c6dde1d84
    }

    @Override
    public int getItemCount() {
<<<<<<< HEAD
        return shops.size();
    }

    public static class AllShopViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewAllShop;
        TextView tvNameShop,tvDescriptionShop;
        ImageView imageAllShop;
        public AllShopViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewAllShop = itemView.findViewById(R.id.cardViewAllShop);
            tvNameShop = itemView.findViewById(R.id.tvNameShop);
            tvDescriptionShop = itemView.findViewById(R.id.tvDescriptionShop);
            imageAllShop = itemView.findViewById(R.id.imageAllShop);
=======
        return list_shop.size();
    }

    public static class AllShopViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameShop,tvDescriptionShop;
        public AllShopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameShop = itemView.findViewById(R.id.tvNameShop);
            tvDescriptionShop = itemView.findViewById(R.id.tvDescriptionShop);
>>>>>>> f502e4709d88377758c07595d06b704c6dde1d84
        }
    }
}
