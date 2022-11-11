package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.models.Shop.Shop;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecycleNearlyAdapter extends RecyclerView.Adapter<RecycleNearlyAdapter.ViewHolder> {

    Context context;
    ArrayList<Shop> shops;

    public RecycleNearlyAdapter(Context context, ArrayList<Shop> shops) {
        this.context = context;
        this.shops = shops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_nearly_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.tvPlaceName.setText("Aurora Coffee");
        holder.tvPlaceLocation.setText(shop.getAddress());
        holder.cardPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardPlace;
        TextView tvPlaceName;
        TextView tvPlaceLocation;

        public ViewHolder(@NonNull View view) {
            super(view);
            cardPlace = view.findViewById(R.id.cardPlace);
            tvPlaceName = view.findViewById(R.id.tvPlaceName);
            tvPlaceLocation = view.findViewById(R.id.tvPlaceLocation);
        }
    }

}