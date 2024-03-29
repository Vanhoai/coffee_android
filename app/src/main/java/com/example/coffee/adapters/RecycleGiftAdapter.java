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
import com.example.coffee.utils.HelperFunction;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;

public class RecycleGiftAdapter extends RecyclerView.Adapter<RecycleGiftAdapter.ViewHolder>{

    private final Context context;
    private final ArrayList<Mission> missions;

    public RecycleGiftAdapter(Context context, ArrayList<Mission> missions){
        this.context = context;
        this.missions = missions;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_gift, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mission mission = missions.get(position);
        Logger.log("MISSIONS", mission);
        holder.imagePromo.setImageResource(HelperFunction.getDrawable(mission.getType().getPercent()));
        holder.tvName.setText(mission.getName());
        holder.tvCount.setText(String.format("%d / %d", mission.getCurrent(), mission.getTotal()));
        holder.tvDescription.setText(mission.getDescription());
        holder.tvExpired.setText(HelperFunction.getDifferenceHour(mission.getExpiredAt()));
        holder.cardPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return missions.size();
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
