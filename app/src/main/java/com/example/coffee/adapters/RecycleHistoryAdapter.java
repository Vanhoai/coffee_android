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
import com.example.coffee.models.User.History;

import java.util.ArrayList;

public class RecycleHistoryAdapter extends RecyclerView.Adapter<RecycleHistoryAdapter.HistoryViewHolder> {
    private final Context context;
    private final ArrayList<History> list_history;

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
        holder.tvNameHistory.setText(history.getNameHistory());
        holder.tvPriceHistory.setText(history.getPriceHistory());
        holder.tvDerscriptionHistory.setText(history.getDescription());
        holder.tvDateHistory.setText(String.valueOf(history.getDateHistory()));

    }

    @Override
    public int getItemCount() {
        return list_history.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameHistory, tvPriceHistory, tvDerscriptionHistory, tvDateHistory;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameHistory = itemView.findViewById(R.id.tvNameHistory);
            tvPriceHistory = itemView.findViewById(R.id.tvPriceHistory);
            tvDerscriptionHistory = itemView.findViewById(R.id.tvDescriptionHisstory);
            tvDateHistory = itemView.findViewById(R.id.tvDateHistory);

        }
    }
}
