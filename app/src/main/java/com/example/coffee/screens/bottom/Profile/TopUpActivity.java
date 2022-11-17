package com.example.coffee.screens.bottom.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.screens.bottom.MainActivity;

public class TopUpActivity extends AppCompatActivity {

    ImageView backNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        // mapping
        backNavigation = findViewById(R.id.backNavigation);

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}