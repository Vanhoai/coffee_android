package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.models.User.History;
import com.example.coffee.screens.bottom.Profile.RewardDetailActivity;
import com.example.coffee.screens.bottom.Shop.DetailPlaceActivity;
import com.example.coffee.utils.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecycleHistoryAdapter extends RecyclerView.Adapter<RecycleHistoryAdapter.HistoryViewHolder> {
    private final Context context;
    private final ArrayList<History> list_history;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd - MM - yyyy");


    public RecycleHistoryAdapter(Context context, ArrayList<History> list_history) {
        this.context = context;
        this.list_history = list_history;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = list_history.get(position);
        Logger.log("history", history);
        holder.tvNameHistory.setText("Order Successfully");
        holder.tvPriceHistory.setText(String.valueOf(history.getPrice()));
        holder.tvDerscriptionHistory.setText("Congratulation, you have successfully made a coffee purchase");
        holder.tvDateHistory.setText(String.valueOf(simpleDateFormat.format(history.getDate())));
        holder.cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailPlaceActivity.class); Bundle bundle =new Bundle();
                bundle.putInt("id", history.getOrder().getShop().getId());
                bundle.putString("status", "HISTORY");
                bundle.putSerializable("OrderDetail", history.getOrder());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list_history.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameHistory, tvPriceHistory, tvDerscriptionHistory, tvDateHistory;
        CardView cardHistory;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameHistory = itemView.findViewById(R.id.tvNameHistory);
            tvPriceHistory = itemView.findViewById(R.id.tvPriceHistory);
            tvDerscriptionHistory = itemView.findViewById(R.id.tvDescriptionHisstory);
            tvDateHistory = itemView.findViewById(R.id.tvDateHistory);
            cardHistory = itemView.findViewById(R.id.cardHistory);

        }
    }
}
