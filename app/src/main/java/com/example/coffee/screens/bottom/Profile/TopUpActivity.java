package com.example.coffee.screens.bottom.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ea.async.instrumentation.Main;
import com.example.coffee.R;
import com.example.coffee.callbacks.BalanceCallback;
import com.example.coffee.models.User.BalanceResponse;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.services.UserService;
import com.example.coffee.utils.LayoutLoading;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

public class TopUpActivity extends AppCompatActivity {

    private ImageView backNavigation;
    private AppCompatButton btn50, btn100, btn200, btn250, btnPayNow;
    private EditText edtCardNumber, edtCardHolder, edtDate, edtCVC, edtNominal;
    private UserService userService;
    private ConstraintLayout constraintLayout;
    private LayoutLoading layoutLoading;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        // init view
        initView();

        // init service
        userService = new UserService();

        // handle click
        handleClick();
    }

    private void handleClick() {
        Drawable backgroundInput = getResources().getDrawable(R.drawable.background_input);
        Drawable backgroundButton = getResources().getDrawable(R.drawable.background_button);

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn50.setBackground(backgroundButton);
                btn50.setTextColor(Color.parseColor("#FFFFFF"));
                btn100.setBackground(backgroundInput);
                btn100.setTextColor(Color.parseColor("#343434"));
                btn200.setBackground(backgroundInput);
                btn200.setTextColor(Color.parseColor("#343434"));
                btn250.setBackground(backgroundInput);
                btn250.setTextColor(Color.parseColor("#343434"));
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn50.setBackground(backgroundInput);
                btn50.setTextColor(Color.parseColor("#343434"));
                btn100.setBackground(backgroundButton);
                btn100.setTextColor(Color.parseColor("#FFFFFF"));
                btn200.setBackground(backgroundInput);
                btn200.setTextColor(Color.parseColor("#343434"));
                btn250.setBackground(backgroundInput);
                btn250.setTextColor(Color.parseColor("#343434"));
            }
        });
        btn200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn50.setBackground(backgroundInput);
                btn50.setTextColor(Color.parseColor("#343434"));
                btn100.setBackground(backgroundInput);
                btn100.setTextColor(Color.parseColor("#343434"));
                btn200.setBackground(backgroundButton);
                btn200.setTextColor(Color.parseColor("#FFFFFF"));
                btn250.setBackground(backgroundInput);
                btn250.setTextColor(Color.parseColor("#343434"));
            }
        });
        btn250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn50.setBackground(backgroundInput);
                btn50.setTextColor(Color.parseColor("#343434"));
                btn100.setBackground(backgroundInput);
                btn100.setTextColor(Color.parseColor("#343434"));
                btn200.setBackground(backgroundInput);
                btn200.setTextColor(Color.parseColor("#343434"));
                btn250.setBackground(backgroundButton);
                btn250.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber = edtCardNumber.getText().toString().trim();
                String cardHolder = edtCardHolder.getText().toString().trim();
                String date = edtDate.getText().toString().trim();
                String cvc = edtCVC.getText().toString().trim();
                String nominal = edtNominal.getText().toString().trim();

                layoutLoading.setLoading();
                User user = UserInformation.getUser(TopUpActivity.this);
                userService.topUp(user.getId(), cardNumber, Float.parseFloat(nominal), balanceCallback);
            }
        });
    }

    private void updateUser(BalanceResponse balanceResponse) {
        User user = UserInformation.getUser(TopUpActivity.this);
        user.setBalance(balanceResponse.getBalance());
        boolean check = UserInformation.setUser(TopUpActivity.this, user);
        if (!check) {
            Toast.makeText(this, "UPDATE USER FAILED", Toast.LENGTH_SHORT).show();
            return;
        }
        layoutLoading.setGone();
        Intent intent = new Intent(TopUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private final BalanceCallback balanceCallback = new BalanceCallback() {
        @Override
        public void onSuccess(Boolean value, BalanceResponse balanceResponse) {
            Logger.log("RESPONSE", balanceResponse);
            updateUser(balanceResponse);
        }

        @Override
        public void onFailed(Boolean value) {
            layoutLoading.setGone();
            Toast.makeText(TopUpActivity.this, "TOP UP FAILED", Toast.LENGTH_SHORT).show();
        }
    };

    private void initView() {
        btn50 = findViewById(R.id.btn50);
        btn100 = findViewById(R.id.btn100);
        btn200 = findViewById(R.id.btn200);
        btn250 = findViewById(R.id.btn250);
        btnPayNow = findViewById(R.id.btnPayNow);
        edtCardNumber = findViewById(R.id.edtCardNumber);
        edtCardHolder = findViewById(R.id.edtCardHolder);
        edtDate = findViewById(R.id.edtDate);
        edtCVC = findViewById(R.id.edtCVC);
        edtNominal = findViewById(R.id.edtNomial);
        backNavigation = findViewById(R.id.backNavigation);
        constraintLayout = findViewById(R.id.loading);
        layoutLoading = new LayoutLoading(constraintLayout, TopUpActivity.this);
        layoutLoading.setGone();
    }
}