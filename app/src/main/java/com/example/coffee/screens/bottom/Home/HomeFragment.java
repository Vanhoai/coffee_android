package com.example.coffee.screens.bottom.Home;
import android.annotation.SuppressLint;
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
import com.example.coffee.adapters.RecycleNearlyAdapter;
import com.example.coffee.adapters.RecycleProductAdapter;
import com.example.coffee.callbacks.GiftCallback;
import com.example.coffee.callbacks.GiftOfUserCallback;
import com.example.coffee.callbacks.ProductCallback;
import com.example.coffee.callbacks.ShopCallback;
import com.example.coffee.models.Order.Gift;
import com.example.coffee.models.Product.Product;

import com.example.coffee.models.Product.ProductResponse;
import com.example.coffee.models.Shop.GiftResponse;
import com.example.coffee.models.Shop.Shop;


import com.example.coffee.models.Shop.ShopResponse;
import com.example.coffee.models.User.User;

import com.example.coffee.screens.bottom.Product.ProductListActivity;
import com.example.coffee.screens.bottom.Profile.HistoryActivity;
import com.example.coffee.screens.bottom.Profile.RewardDetailActivity;
import com.example.coffee.screens.bottom.Profile.TopUpActivity;
import com.example.coffee.screens.bottom.Shop.PlaceListActivity;
import com.example.coffee.services.GiftService;
import com.example.coffee.services.ProductService;
import com.example.coffee.services.ShopService;
import com.example.coffee.utils.HelperFunction;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    
     RecyclerView recycleViewNearbyPlace, recycleViewBestSeller;
     ImageView imageTopUp, imagePay, imagePromo, imageHistory, imageNotify;
     TextView tvBalance, tvViewAllBestSeller, tvViewAllNearbyPlace, tvViewAllMyReward;
     ImageView imageReward;
     TextView tvNameReward;
     TextView tvDescription;
     ShopService shopService;
     ProductService productService;
     ArrayList<Product> products;
     ArrayList<Shop> shops;
     ArrayList<Gift> gifts;
     GiftService giftService;
     LinearLayout linearMyReward;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.home_fragment, container, false);

        // init
        init(view);

        // init data
        shops = new ArrayList<>();
        products = new ArrayList<>();
        gifts = new ArrayList<>();

        // init service
        shopService = new ShopService();
        productService = new ProductService();
        giftService = new GiftService();

        // call api
        initShop();
        initProduct();
        initReward();

        // set view
        setView();

        // handle onclick
        handleOnClick();

        return view;
    }

    private void updateDeviceToken() {

    }

    private void setView() {
        User user = UserInformation.getUser(getContext());
        @SuppressLint("DefaultLocale") String balance = String.format("%.0f VND",user.getBalance().getAmount());
        tvBalance.setText(balance);
    }

    public void initShop() {
        shopService.getShops(5, 0, "ASC", "id", new ShopCallback() {
            @Override
            public void onSuccess(boolean value, ShopResponse shopResponse) {
                Logger.log("SHOPS", shopResponse);
                shops.addAll(shopResponse.getShops());
                renderPlace(recycleViewNearbyPlace, shops);
            }

            @Override
            public void onFailed(boolean value) {
                Logger.log("RESPONSE", "ERROR");
            }
        });
    }

    public void initProduct() {
        productService.getProducts(5, 0, "ASC", "id", new ProductCallback() {
            @Override
            public void onSuccess(boolean value, ProductResponse productResponse) {
                Logger.log("PRODUCT", productResponse);
                products.addAll(productResponse.getProducts());
                renderProduct(recycleViewBestSeller, products);
            }

            @Override
            public void onFailed(boolean value) {
                Logger.log("RESPONSE", "ERROR");
            }
        });
    }

    public void initReward(){
        User user = UserInformation.getUser(getContext());
        giftService.getGift(5, user.getId(), new GiftCallback() {
            @Override
            public void onSuccess(boolean value, GiftResponse giftResponse) {
                if (giftResponse.getTotal().getListGifts().size() <= 0) {
                    linearMyReward.setVisibility(View.GONE);
                    return;
                }
                Logger.log("REWARD", giftResponse);
                Gift gift = giftResponse.getTotal().getListGifts().get(0);
                imageReward.setImageResource(HelperFunction.getDrawable(gift.getType().getPercent()));
                tvNameReward.setText(gift.getName());
                tvDescription.setText(String.format("Gift will expire on %d hours", HelperFunction.getDifferenceHour(gift.getExpiredAt())));

                int result = HelperFunction.getDifferenceHour(gift.getExpiredAt());
                Logger.log("RESULT", result);
            }

            @Override
            public void onFailed(boolean value) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void init(View view) {
        recycleViewNearbyPlace = view.findViewById(R.id.recycleViewNearbyPlace);
        recycleViewBestSeller = view.findViewById(R.id.recycleViewBestSeller);
        imageTopUp = view.findViewById(R.id.imageTopUp);
        imagePay = view.findViewById(R.id.imagePay);
        imagePromo = view.findViewById(R.id.imagePromo);
        imageHistory = view.findViewById(R.id.imageHistory);
        imageNotify = view.findViewById(R.id.imageNotify);
        tvBalance = view.findViewById(R.id.tvBalance);
        tvViewAllMyReward = view.findViewById(R.id.tvViewAllMyReward);
        tvViewAllBestSeller = view.findViewById(R.id.tvViewHomeBestSeller);
        tvViewAllNearbyPlace = view.findViewById(R.id.tvViewHomeNearby);
        imageReward = view.findViewById(R.id.imageReward);
        tvNameReward = view.findViewById(R.id.tvNameReward);
        tvDescription = view.findViewById(R.id.tvDescription);
        linearMyReward = view.findViewById(R.id.linearMyReward);
    }

    private void handleOnClick () {
        imageTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TopUpActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        imagePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CardActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        imagePromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PromoActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        imageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        imageNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        tvViewAllMyReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RewardDetailActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        tvViewAllBestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Page Title", "Best Seller");
                intent.putExtras(bundle);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        tvViewAllNearbyPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlaceListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Page Title", "Nearby Place");
                intent.putExtras(bundle);
                startActivity(intent);
                requireActivity().finish();
            }
        });
    }

    private void renderPlace(RecyclerView recyclerView, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleNearlyAdapter adapter = new RecycleNearlyAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
    }

    private void renderProduct(RecyclerView recyclerView, ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleProductAdapter adapter = new RecycleProductAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
    }

}
