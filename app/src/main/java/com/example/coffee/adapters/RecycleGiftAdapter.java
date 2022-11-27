package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.models.Shop.Mission;

import java.util.ArrayList;

public class RecycleGiftAdapter extends RecyclerView.Adapter<RecycleGiftAdapter.ViewHolder>{

    Context context;
    ArrayList<Mission> missions;

    public RecycleGiftAdapter(Context context, ArrayList<Mission> missions){
        this.context = context;
        this.missions = missions;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_gift, parent, false);
        return new RecycleGiftAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mission mission = missions.get(position);
        holder.imagePromo.setImageResource(R.drawable.promo1);
        holder.tvDescription.setText(mission.getDescription());
        holder.tvExpired.setText(String.format("Ends in %d hours", 12));
        holder.tvCount.setText(String.format("%d/10", mission.getCurrent()));
        holder.tvName.setText(mission.getName());
        holder.cardPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagePromo;
        TextView tvName;
        TextView tvDescription;
        TextView tvCount;
        TextView tvExpired;
        CardView cardPromo;

        public ViewHolder(@NonNull View view) {
            super(view);
            imagePromo = view.findViewById(R.id.imagePromo);
            tvName = view.findViewById(R.id.tvNamePromo);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvExpired = view.findViewById(R.id.tvExpired);
            tvCount = view.findViewById(R.id.tvCount);
            cardPromo = view.findViewById(R.id.cardPromo);
        }
    }
}
