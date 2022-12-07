package com.example.coffee.screens.bottom.Home;

import static com.example.coffee.R.id.tvCode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ea.async.instrumentation.Main;
import com.example.coffee.R;
import com.example.coffee.models.User.Balance;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.screens.bottom.Profile.TopUpActivity;
import com.example.coffee.utils.UserInformation;

public class CardActivity extends AppCompatActivity {

    private ImageView imageBack;
    private TextView tvUserName;
    private TextView tvBalance;
    private TextView tvCode;
    LinearLayout TopUpCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        //init View
        initView();

        // set view
        User user = UserInformation.getUser(CardActivity.this);
        Balance balance = user.getBalance();
        tvUserName.setText(user.getUsername());
        tvBalance.setText(String.format("%.0f",balance.getAmount()));
        tvCode.setText(balance.getCode());

        // handle click
       handleClick();
    }

    public void initView(){
        imageBack = findViewById(R.id.imageBack);
        tvUserName = findViewById(R.id.tvUserName);
        tvBalance = findViewById(R.id.tvBalance);
        tvCode = findViewById(R.id.tvCode);
    }

    public void handleClick(){
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TopUpCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardActivity.this, TopUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}