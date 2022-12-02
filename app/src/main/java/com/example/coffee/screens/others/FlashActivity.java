package com.example.coffee.screens.others;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.ea.async.instrumentation.Main;
import com.example.coffee.R;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.utils.Storage;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        }, 10000);
    }

    public void next() {
        Intent intent;
        SharedPreferences sharedPreferences = getSharedPreferences("CHECK_LOGIN", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("SIGN_IN", false);
        if (check) {
            intent = new Intent(FlashActivity.this, MainActivity.class);
        } else {
            intent = new Intent(FlashActivity.this, OnBoardActivity.class);
        }
        startActivity(intent);
        finish();
    }
}