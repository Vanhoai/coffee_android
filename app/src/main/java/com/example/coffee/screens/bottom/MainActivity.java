package com.example.coffee.screens.bottom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;

import com.example.coffee.R;
import com.example.coffee.fcm.UseFCM;
import com.example.coffee.screens.bottom.Gift.GiftFragment;
import com.example.coffee.screens.bottom.Home.HomeFragment;
import com.example.coffee.screens.bottom.Profile.ProfileFragment;
import com.example.coffee.screens.bottom.Shop.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UseFCM.getDeviceToken();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setItemIconTintList(null);

        replaceFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_icon:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.shop_icon:
                        replaceFragment(new ShopFragment());
                        break;
                    case  R.id.gift_icon:
                        replaceFragment(new GiftFragment());
                        break;
                    case R.id.profile_icon:
                        replaceFragment(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }
}