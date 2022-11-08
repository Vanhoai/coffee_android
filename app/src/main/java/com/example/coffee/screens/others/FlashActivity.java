package com.example.coffee.screens.others;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.coffee.R;
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
        }, 2000);
    }

    public void next() {
        // check if login then go to home screen
        Intent intent = new Intent(FlashActivity.this, OnBoardActivity.class);
        startActivity(intent);
        finish();
    }
}