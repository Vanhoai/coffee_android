package com.example.coffee.screens.bottom.Gift;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleGiftAdapter;
import com.example.coffee.adapters.RecyclePromoAdapter;
import com.example.coffee.callbacks.GiftCallback;
import com.example.coffee.models.Order.Gift;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.GiftResponse;
import com.example.coffee.models.Shop.Mission;
import com.example.coffee.models.Shop.Total;
import com.example.coffee.models.User.User;
import com.example.coffee.services.GiftService;
import com.example.coffee.utils.HelperFunction;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

import java.util.ArrayList;

public class GiftFragment extends Fragment {
    TextView tvtotalGift;
    TextView tvtotalMission;
    TextView tvtotalMissionProgress;
    TextView tvViewAllGift;
    TextView tvViewAllMission;
    ImageView imagePromo;
    TextView tvName;
    TextView tvDescription;
    TextView tvExpired;
    TextView tvXP;
    RecyclerView recyclerMission;
    ArrayList<Gift> gifts;
    ArrayList<Mission> missions;
    GiftService giftService;
    Total total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.gift_fragment, container, false);

       //init View
        intView(view);

        //handle click
        handleClick();

        //init data
        gifts = new ArrayList<>();
        missions = new ArrayList<>();
        giftService = new GiftService();


        //call API
        initMission();




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void intView (View view){
        recyclerMission = view.findViewById(R.id.recyclerMission);
        tvtotalGift = view.findViewById(R.id.tvtotalGift);
        tvtotalMission = view.findViewById(R.id.tvtotalMission);
        tvtotalMissionProgress = view.findViewById(R.id.tvtotalMissionProgress);
        tvViewAllGift = view.findViewById(R.id.tvViewAllGift);
        tvViewAllMission = view.findViewById(R.id.tvViewAllMission);
        imagePromo = view.findViewById(R.id.imagePromo);
        tvName = view.findViewById(R.id.tvNamePromo);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvExpired = view.findViewById(R.id.tvExpired);
        tvXP = view.findViewById(R.id.tvXP);
    }

    public void handleClick(){
        tvViewAllGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvViewAllMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void initMission(){
        User user = UserInformation.getUser(requireContext());
        Logger.log("USER", user);
        giftService.getGift(5, user.getId(), new GiftCallback() {
            @Override
            public void onSuccess(boolean value, GiftResponse giftResponse) {
                Logger.log("MISSION", giftResponse);
                missions.addAll(giftResponse.getTotal().getListMissions());
                renderGift(recyclerMission, missions);
                tvtotalGift.setText(String.valueOf(giftResponse.getTotal().getTotalGift()));
                tvtotalMission.setText(String.valueOf(giftResponse.getTotal().getTotalMission()));
                tvtotalMissionProgress.setText(String.valueOf(giftResponse.getTotal().getTotalMissionProgress()));

                Gift gift = giftResponse.getTotal().getListGifts().get(0);
                imagePromo.setImageResource(HelperFunction.getDrawable(gift.getType().getPercent()));
                tvName.setText(gift.getName());
                tvDescription.setText(String.valueOf(gift.getExpiredAt()));

                tvXP.setText(String.format("%d XP more to get rewards" ,user.getExp()));
            }

            @Override
            public void onFailed(boolean value) {
                Logger.log("MISSION", "ERROR");
            }
        });
    }

    public void renderGift(RecyclerView recyclerMission, ArrayList<Mission> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerMission.setLayoutManager(linearLayoutManager);
        RecycleGiftAdapter adapter = new RecycleGiftAdapter(getContext(),data);
        recyclerMission.setAdapter(adapter);
    }
}
