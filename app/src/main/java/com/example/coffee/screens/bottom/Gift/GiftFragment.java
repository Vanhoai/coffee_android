package com.example.coffee.screens.bottom.Gift;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.coffee.screens.bottom.Home.PromoActivity;
import com.example.coffee.services.GiftService;
import com.example.coffee.utils.HelperFunction;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

import java.util.ArrayList;

public class GiftFragment extends Fragment {

    private TextView tvTotalGift, tvTotalMission, tvTotalMissionProgress, tvViewAllGift, tvViewAllMission, tvDescription, tvExpired, tvExp, tvName;
    private ImageView imagePromo;
    private RecyclerView recyclerMission;
    private GiftService giftService;
    private ArrayList<Mission> missions;
    private LinearLayout cardGift;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.gift_fragment, container, false);

       // init View
        intView(view);

        // handle click
        handleClick();

        // init data
        missions = new ArrayList<>();

        // init service
        giftService = new GiftService();

        // call API
        initMission();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void intView (View view){
        recyclerMission = view.findViewById(R.id.recyclerMission);
        tvTotalGift = view.findViewById(R.id.tvtotalGift);
        tvTotalMission = view.findViewById(R.id.tvtotalMission);
        tvTotalMissionProgress = view.findViewById(R.id.tvtotalMissionProgress);
        tvViewAllGift = view.findViewById(R.id.tvViewAllGift);
        tvViewAllMission = view.findViewById(R.id.tvViewAllMission);
        imagePromo = view.findViewById(R.id.imagePromo);
        tvName = view.findViewById(R.id.tvNamePromo);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvExpired = view.findViewById(R.id.tvExpired);
        tvExp = view.findViewById(R.id.tvXP);
        cardGift = view.findViewById(R.id.cardGift);
    }

    private void handleClick(){
        tvViewAllGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvViewAllMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PromoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Page Title", "Promo");
                intent.putExtras(bundle);
                startActivity(intent);
                requireActivity().finish();
            }
        });
    }

    private void initMission(){
        try {
            User user = UserInformation.getUser(requireContext());
            Logger.log("USER", user);
            giftService.getGift(5, user.getId(), new GiftCallback() {
                @Override
                public void onSuccess(boolean value, GiftResponse giftResponse) {
                    Logger.log("MISSION", giftResponse);
                    missions.addAll(giftResponse.getTotal().getListMissions());
                    renderGift(recyclerMission, missions);
                    tvTotalGift.setText(String.valueOf(giftResponse.getTotal().getTotalGift()));
                    tvTotalMission.setText(String.valueOf(giftResponse.getTotal().getTotalMission()));
                    tvTotalMissionProgress.setText(String.format("%d missions progress", giftResponse.getTotal().getTotalMissionProgress()));


                    if (giftResponse.getTotal().getListGifts().size() <= 0) {
                        cardGift.setVisibility(View.GONE);
                    } else {
                        Gift gift = giftResponse.getTotal().getListGifts().get(0);
                        imagePromo.setImageResource(HelperFunction.getDrawable(gift.getType().getPercent()));
                        tvName.setText(gift.getName());
                        tvDescription.setText(HelperFunction.getDifferenceHour(gift.getExpiredAt()));
                        tvExp.setText(String.format("%d XP more to get rewards", user.getExp()));
                    }
                }
                @Override
                public void onFailed(boolean value) {
                    Logger.log("MISSION", "ERROR");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void renderGift(RecyclerView recyclerMission, ArrayList<Mission> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerMission.setLayoutManager(linearLayoutManager);
        RecycleGiftAdapter adapter = new RecycleGiftAdapter(getContext(), data);
        recyclerMission.setAdapter(adapter);
    }
}
