package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.models.Shop.Shop;

import java.util.ArrayList;

public class RecycleAllShopAdapter extends RecyclerView.Adapter<RecycleAllShopAdapter.AllShopViewHolder> {
    private Context context;
    private ArrayList<Shop> list_shop;

    public RecycleAllShopAdapter(Context context, ArrayList<Shop> list_shop) {
        this.context = context;
        this.list_shop = list_shop;
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
        Shop shop = list_shop.get(position);
        holder.tvNameShop.setText("Aurora Coffee");
        holder.tvDescriptionShop.setText(shop.getDescription());
    }

    @Override
    public int getItemCount() {
        return list_shop.size();
    }

    public static class AllShopViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameShop,tvDescriptionShop;
        public AllShopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameShop = itemView.findViewById(R.id.tvNameShop);
            tvDescriptionShop = itemView.findViewById(R.id.tvDescriptionShop);
        }
    }
}
