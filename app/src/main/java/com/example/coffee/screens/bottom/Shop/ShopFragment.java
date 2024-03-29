package com.example.coffee.screens.bottom.Shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.example.coffee.adapters.RecycleAllShopAdapter;
import com.example.coffee.callbacks.ProductCallback;
import com.example.coffee.callbacks.PromoCallback;
import com.example.coffee.callbacks.ShopCallback;
import com.example.coffee.models.Product.ProductResponse;
import com.example.coffee.models.Shop.Mission;
import com.example.coffee.models.Shop.PromoResponse;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.models.Shop.ShopResponse;
import com.example.coffee.screens.bottom.Home.PromoActivity;
import com.example.coffee.services.GiftService;
import com.example.coffee.services.ProductService;
import com.example.coffee.services.ShopService;
import com.example.coffee.utils.HelperFunction;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class ShopFragment extends Fragment {
    
    private RecyclerView recyclerViewNearbyPlace, recyclerViewAllShop;
    private TextView tvViewAllHottest, tvViewAllShop, tvViewAllNearby;
    private ShopService shopService;
    private ArrayList<Shop> shopsNearby;
    private ArrayList<Shop> shopsAllShop;
    private EditText edtSearchProduct;
    private ProductService productService;
    ImageView imageHottest;
    TextView tvNameHottest, tvExpired;
    TextView tvDescription;
    TextView tvCount;
    LinearLayout cardHottest;
    GiftService giftService;
    ArrayList<Mission> missions;

    CompositeDisposable disposable = new CompositeDisposable();
    BehaviorSubject<String> _textInput = BehaviorSubject.create();
    Flowable<String> textInput = _textInput.toFlowable(BackpressureStrategy.LATEST);

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.shop_fragment, container, false);

        //init view
        init(view);

        // init data
        shopsNearby = new ArrayList<>();
        shopsAllShop = new ArrayList<>();
        missions = new ArrayList<>();

        // init service
        shopService = new ShopService();
        giftService = new GiftService();
        productService = new ProductService();

        // call api
        initShop();
        initAllShop();
        initPromo();

        // handle onclick
        handleOnClick();

        handleOnchange();

        return view;
    }

    private void handleOnchange() {
        edtSearchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
//                productService.searchProducts(value, productCallback);
                _textInput.onNext(value);
            }
        });
    }

    private final ProductCallback productCallback = new ProductCallback() {
        @Override
        public void onSuccess(boolean value, ProductResponse productResponse) {
            Logger.log("RESPONSE", productResponse);
        }

        @Override
        public void onFailed(boolean value) {

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void init(View view){
        recyclerViewNearbyPlace = view.findViewById(R.id.recycleViewNearbyPlace);
        recyclerViewAllShop = view.findViewById(R.id.recycleViewAllShop);
        tvViewAllHottest = view.findViewById(R.id.tvViewAllHottest);
        tvViewAllShop = view.findViewById(R.id.tvViewShopAll);
        tvViewAllNearby = view.findViewById(R.id.tvViewShopNearby);
        cardHottest = view.findViewById(R.id.cardHottest);
        tvDescription =view.findViewById(R.id.tvDescription);
        tvNameHottest = view.findViewById(R.id.tvNameHottest);
        imageHottest = view.findViewById(R.id.imageHottest);
        tvCount = view.findViewById(R.id.tvCount);
        tvExpired = view.findViewById(R.id.tvExpired);
        edtSearchProduct = view.findViewById(R.id.edtSearchProduct);
    }

    public void handleOnClick(){
        tvViewAllShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlaceListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Page Title", "All Shop");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tvViewAllHottest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PromoActivity.class);
                startActivity(intent);
            }
        });
        tvViewAllNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlaceListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Page Title", "Nearby Place");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void initShop() {
        shopService.getShops(5, 0, "ASC", "id", new ShopCallback() {
            @Override
            public void onSuccess(boolean value, ShopResponse shopResponse) {
                Logger.log("SHOPS", shopResponse);
                shopsNearby.addAll(shopResponse.getShops());
                renderPlace(recyclerViewNearbyPlace, shopsNearby);
            }

            @Override
            public void onFailed(boolean value) {
                Logger.log("RESPONSE", "ERROR");
            }
        });
    }

    public void initAllShop(){
        shopService.getAllShop(new ShopCallback() {
            @Override
            public void onSuccess(boolean value, ShopResponse shopResponse) {
                Logger.log("ALL SHOPS", shopResponse);

                shopsAllShop.addAll(shopResponse.getShops());
                renderAllShop(recyclerViewAllShop, shopsAllShop);
            }

            @Override
            public void onFailed(boolean value) {
                Logger.log("RESPONSE", "ERROR");
            }
        });
    }

    private void initPromo(){
        try {
            giftService.getPromo(5, new PromoCallback() {
                @Override
                public void onSuccess(boolean value, PromoResponse promoResponse) {
                    if (promoResponse.getPromo().getHottest() != null) {
                        Mission mission = promoResponse.getPromo().getHottest();
                        imageHottest.setImageResource(HelperFunction.getDrawable(mission.getType().getPercent()));
                        tvNameHottest.setText(mission.getName());
                        tvDescription.setText(mission.getDescription());
                        tvCount.setVisibility(View.GONE);
                        tvExpired.setText(HelperFunction.getDifferenceHour(mission.getExpiredAt()));
                        return;
                    }
                    cardHottest.setVisibility(View.GONE);
                }

                @Override
                public void onFailed(boolean value) {
                    Logger.log("PROMO", "ERROR");
                    cardHottest.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void renderPlace(RecyclerView recyclerViewNearbyPlace, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewNearbyPlace.setLayoutManager(linearLayoutManager);
        RecycleNearlyAdapter adapter = new RecycleNearlyAdapter(getContext(), data);
        recyclerViewNearbyPlace.setAdapter(adapter);
    }

    public void renderAllShop(RecyclerView recyclerViewAllShop, ArrayList<Shop> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewAllShop.setLayoutManager(linearLayoutManager);
        RecycleAllShopAdapter adapter = new RecycleAllShopAdapter(getContext(), data);
        recyclerViewAllShop.setAdapter(adapter);
    }

}
