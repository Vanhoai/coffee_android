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
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.app.Constants;
import com.example.coffee.callbacks.HistoryCallback;
import com.example.coffee.callbacks.OrderDetailCallback;
import com.example.coffee.models.Order.Order;
import com.example.coffee.models.Order.OrderDetailResponse;
import com.example.coffee.models.User.History;
import com.example.coffee.models.User.HistoryResponse;
import com.example.coffee.screens.bottom.Profile.RewardDetailActivity;
import com.example.coffee.screens.bottom.Shop.DetailPlaceActivity;
import com.example.coffee.services.OrderService;
import com.example.coffee.utils.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecycleHistoryAdapter extends RecyclerView.Adapter<RecycleHistoryAdapter.HistoryViewHolder> {

    private final Context context;
    private final ArrayList<History> list_history;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd - MM - yyyy");
    private final OrderService orderService;

    public RecycleHistoryAdapter(Context context, ArrayList<History> list_history) {
        this.context = context;
        this.list_history = list_history;
        orderService = new OrderService();
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
        Logger.log("status", history.getOrder().getStatus());

        if (history.getOrder().getStatus() == Constants.CANCELED_STATUS) { // 4
            holder.tvNameHistory.setText("Đơn hàng đã hủy");
            holder.tvDerscriptionHistory.setText("Bạn đã hủy đơn hàng này");
            holder.tvStatus.setText("");
            holder.btnCancel.setVisibility(View.GONE);
        } else if (history.getOrder().getStatus() == Constants.DELIVERED_STATUS) { // 3
            holder.tvNameHistory.setText("Đặt hàng thành công");
            holder.tvStatus.setText(String.valueOf(history.getOrder().getTotal()));
            holder.tvDerscriptionHistory.setText("Chúc mừng bạn đã đặt hàng thành công !!");
            holder.btnCancel.setVisibility(View.GONE);
        } else {
            holder.tvNameHistory.setText("Đơn hàng chưa hoàn thành");
            holder.tvStatus.setText(String.valueOf(history.getOrder().getTotal()));
            holder.tvDerscriptionHistory.setVisibility(View.GONE);
            holder.btnCancel.setText("Cancel Order");
        }

        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderService.cancelOrder(history.getOrder().getId(), historyCallback);
            }
        });

        holder.tvDateHistory.setText(String.valueOf(simpleDateFormat.format(history.getDate())));
        holder.cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (history.getOrder().getStatus() != Constants.DELIVERED_STATUS) { // 3
                    Intent intent = new Intent(context, DetailPlaceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", history.getOrder().getShop().getId());
                    bundle.putString("status", "HISTORY");
                    bundle.putSerializable("OrderDetail", history.getOrder());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_history.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameHistory, tvStatus, tvDerscriptionHistory, tvDateHistory;
        CardView cardHistory;
        AppCompatButton btnCancel;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameHistory = itemView.findViewById(R.id.tvNameHistory);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDerscriptionHistory = itemView.findViewById(R.id.tvDescriptionHisstory);
            tvDateHistory = itemView.findViewById(R.id.tvDateHistory);
            cardHistory = itemView.findViewById(R.id.cardHistory);
            btnCancel = itemView.findViewById(R.id.btnCancel);
        }
    }

    private void render(ArrayList<History> data) {
        list_history.clear();
        list_history.addAll(data);
        notifyDataSetChanged();
    }

    private final HistoryCallback historyCallback = new HistoryCallback() {
        @Override
        public void onSuccess(Boolean value, HistoryResponse historyResponse) {
            render(historyResponse.getHistories());
            Logger.log("RESPONSE", historyResponse);
        }

        @Override
        public void onFailed(Boolean value) {
            Logger.log("RESPONSE", "ERROR");
        }
    };
}
