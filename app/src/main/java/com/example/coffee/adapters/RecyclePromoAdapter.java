package com.example.coffee.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.callbacks.MissionUseCallback;
import com.example.coffee.models.Others.MissionUser;
import com.example.coffee.models.Others.MissionUserResponse;
import com.example.coffee.models.Shop.Mission;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.bottom.Profile.RewardDetailActivity;
import com.example.coffee.services.GiftService;
import com.example.coffee.utils.HelperFunction;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

import java.util.ArrayList;

public class RecyclePromoAdapter extends RecyclerView.Adapter<RecyclePromoAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Mission> missions;
    private final GiftService giftService;

    public RecyclePromoAdapter(Context context, ArrayList<Mission> missions) {
        this.context = context;
        this.missions = missions;
        this.giftService = new GiftService();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_promo, parent, false);
        return new RecyclePromoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mission mission = missions.get(position);
        holder.imagePromo.setImageResource(HelperFunction.getDrawable(mission.getType().getPercent()));
        holder.tvDescription.setText(mission.getDescription());
        holder.tvExpired.setText(HelperFunction.getDifferenceHour(mission.getExpiredAt()));
        holder.tvName.setText(mission.getName());
        holder.tvCount.setVisibility(View.GONE);
        holder.cardPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(mission);
            }
        });
    }

    public void register(Mission mission) {
        User user = UserInformation.getUser(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.register_mission, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        AppCompatButton btnCancel = view.findViewById(R.id.btnCancel);
        AppCompatButton btnOK = view.findViewById(R.id.btnOK);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giftService.registerMission(user.getId(), mission.getId(), new MissionUseCallback() {
                    @Override
                    public void onSuccess(MissionUserResponse missionUserResponse) {
                        alertDialog.dismiss();
                        Logger.log("RESPONSE", missionUserResponse);
                    }

                    @Override
                    public void onFailed(boolean value) {
                        alertDialog.dismiss();
                        Logger.log("RESPONSE", "ERROR");
                    }
                });
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
