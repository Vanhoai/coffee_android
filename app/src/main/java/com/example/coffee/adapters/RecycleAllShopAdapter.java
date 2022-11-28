package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class RecycleAllShopAdapter extends RecyclerView.Adapter<RecycleAllShopAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Shop> shops;

    public RecycleAllShopAdapter(Context context, ArrayList<Shop> shops) {
        this.context = context;
        this.shops = shops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_allshop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.tvNameShop.setText("Aurora Coffee");
        String[] desc = shop.getDescription().split(" ");
        if (desc.length > 15) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 15; i++) {
                result.append(desc[i]);
                if (i < 14) {
                    result.append(" ");
                }
            }
            holder.tvDescriptionShop.setText(String.format("%s ...", result));
        } else {
            holder.tvDescriptionShop.setText(shop.getDescription());
        }
        holder.tvAddress.setText(shop.getAddress());
        Glide.with(context).load(shop.getImage()).into(holder.imageAllShop);
        holder.cardViewAllShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPlaceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", shop.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameShop,tvDescriptionShop, tvAddress;
        CardView cardViewAllShop;
        ImageView imageAllShop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameShop = itemView.findViewById(R.id.tvNameShop);
            tvDescriptionShop = itemView.findViewById(R.id.tvDescriptionShop);
            cardViewAllShop = itemView.findViewById(R.id.cardViewAllShop);
            imageAllShop = itemView.findViewById(R.id.imageAllShop);
            tvAddress = itemView.findViewById(R.id.tvPlaceLocation);
        }
    }
}
