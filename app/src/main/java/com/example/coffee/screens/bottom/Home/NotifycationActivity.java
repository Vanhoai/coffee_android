package com.example.coffee.screens.bottom.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.screens.bottom.MainActivity;

public class NotifycationActivity extends AppCompatActivity {

    ImageView backNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifycation);

        //init View
        initView();

        //handle click
        handleClick();

    }
    public void initView() {
        backNavigation = findViewById(R.id.backNavigation);
    }

    public void handleClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotifycationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}