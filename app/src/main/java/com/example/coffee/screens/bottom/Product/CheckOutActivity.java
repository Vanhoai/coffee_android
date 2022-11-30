package com.example.coffee.screens.bottom.Product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleProductDetailAdapter;
import com.example.coffee.models.Product.Product;
import com.example.coffee.models.Shop.Shop;
import com.example.coffee.screens.bottom.Shop.DetailPlaceActivity;
import com.example.coffee.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckOutActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    private AppCompatButton btnContinuePayment, btnPickUp, btnShip;
    private ImageView backNavigation;
    private ArrayList<Product> products;
    private RecyclerView recycleProducts;
    private TextView tvTotal, tvAmount, tvShip, tvPromo;
    private HashMap<String, Boolean> checkDelivery;
    private LocationManager locationManager;
    private Spinner spinnerCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        // init view
        initView();

        // init data
        products = new ArrayList<>();

        // set view
        initProducts();

        // handle click
        handleOnclick();
    }

    private void calculator() {
        String amountText = tvAmount.getText().toString();
        String shipText = tvShip.getText().toString();
        String promoText = tvPromo.getText().toString();
        float amount = 0, ship = 0, promo = 0;
        try {
            if (!amountText.equals("-")) {
                amount = Float.parseFloat(amountText);
            }
            if (!promoText.equals("-")) {
                promo = Float.parseFloat(promoText);
            }
            if (!shipText.equals("-")) {
                ship = Float.parseFloat(shipText);
            }
            float total = amount + ship - promo;
            tvTotal.setText(String.format("%.0f", total));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void handleOnclick() {


        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStart = getIntent();
                Bundle bundleStart = intentStart.getExtras();
                int id = bundleStart.getInt("id", -1);

                Intent intent = new Intent(CheckOutActivity.this, DetailPlaceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        btnContinuePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this, PaymentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDelivery(true, false);
            }
        });

        btnShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDelivery(false, true);
            }
        });


    }

    private void createOrder() {
        Logger.log("PRODUCTS", products);
    }

    public void initView() {
        btnContinuePayment = findViewById(R.id.btnContinuePayment);
        backNavigation = findViewById(R.id.backNavigation);
        recycleProducts = findViewById(R.id.recycleProducts);
        tvAmount = findViewById(R.id.tvAmount);
        tvPromo = findViewById(R.id.tvPromo);
        tvShip = findViewById(R.id.tvShip);
        tvTotal = findViewById(R.id.tvTotal);
        btnPickUp = findViewById(R.id.btnPickUp);
        btnShip = findViewById(R.id.btnShip);
        checkDelivery = new HashMap<>();
        checkDelivery.put("PICKUP", true);
        checkDelivery.put("SHIP", false);
        spinnerCheckout = findViewById(R.id.spinnerCheckout);

        ActivityCompat.requestPermissions( this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }
    }

    public void changeDelivery(Boolean pickup, Boolean ship) {
        Drawable backgroundInput = getResources().getDrawable(R.drawable.background_input);
        Drawable backgroundButton = getResources().getDrawable(R.drawable.background_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            checkDelivery.replace("PICKUP", pickup);
            checkDelivery.replace("PICKUP", ship);
        }

        if (pickup) {
            btnPickUp.setBackground(backgroundButton);
            btnShip.setBackground(backgroundInput);
            btnPickUp.setTextColor(Color.parseColor("#FFFFFF"));
            btnShip.setTextColor(Color.parseColor("#343434"));
            tvShip.setText("-");
        } else {
            btnShip.setBackground(backgroundButton);
            btnPickUp.setBackground(backgroundInput);
            btnShip.setTextColor(Color.parseColor("#FFFFFF"));
            btnPickUp.setTextColor(Color.parseColor("#343434"));
            tvShip.setText("20000");
        }
        calculator();
    }

    public void initProducts() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            products = (ArrayList<Product>) bundle.getSerializable("products");
            render(products);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void render(ArrayList<Product> data) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckOutActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycleProducts.setLayoutManager(linearLayoutManager);
        RecycleProductDetailAdapter adapter = new RecycleProductDetailAdapter(CheckOutActivity.this, data, new RecycleProductDetailAdapter.UpdateTotal() {
            @Override
            public void update(ArrayList<Product> products) {
                float total = 0;
                for (int i = 0; i < products.size(); i++) {
                    total += products.get(i).getPrice() * products.get(i).getCurrent();
                }
                tvAmount.setText(String.format("%.0f", total));
                calculator();
            }
        });
        recycleProducts.setAdapter(adapter);
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                CheckOutActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                CheckOutActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double latitude = locationGPS.getLatitude();
                double longitude = locationGPS.getLongitude();
                Logger.log("LATITUDE", latitude);
                Logger.log("LONGITUDE", longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}