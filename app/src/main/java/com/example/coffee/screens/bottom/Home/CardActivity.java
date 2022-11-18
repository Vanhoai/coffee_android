package com.example.coffee.screens.bottom.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ea.async.instrumentation.Main;
import com.example.coffee.R;
import com.example.coffee.screens.bottom.MainActivity;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        ImageView imageBack = findViewById(R.id.imageBack);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}