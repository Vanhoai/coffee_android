package com.example.coffee.screens.bottom.Home;

import static com.example.coffee.R.id.tvCode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ea.async.instrumentation.Main;
import com.example.coffee.R;
import com.example.coffee.models.User.Balance;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.utils.UserInformation;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        ImageView imageBack = findViewById(R.id.imageBack);
        TextView tvUserName = findViewById(R.id.tvUserName);
        TextView tvBalance = findViewById(R.id.tvBalance);
        TextView tvCode = findViewById(R.id.tvCode);

        User user = UserInformation.getUser(CardActivity.this);
        Balance balance = user.getBalance();
        tvUserName.setText(user.getUsername());
        tvBalance.setText(String.valueOf(balance.getAmount()));
        tvCode.setText(balance.getCode());


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