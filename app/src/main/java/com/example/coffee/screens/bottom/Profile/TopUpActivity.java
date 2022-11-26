package com.example.coffee.screens.bottom.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.utils.Logger;

public class TopUpActivity extends AppCompatActivity {
    ImageView backNavigation;
    AppCompatButton btn50, btn100, btn200, btn250, btnPayNow;
    EditText edtCardNumber, edtCardHolder, edtDate, edtCVC, edtNomial;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        Drawable backgroundInput = getResources().getDrawable(R.drawable.background_input);
        Drawable backgroundButton = getResources().getDrawable(R.drawable.background_button);

        btn50 = findViewById(R.id.btn50);
        btn100 = findViewById(R.id.btn100);
        btn200 = findViewById(R.id.btn200);
        btn250 = findViewById(R.id.btn250);
        btnPayNow = findViewById(R.id.btnPayNow);

        edtCardNumber = findViewById(R.id.edtCardNumber);
        edtCardHolder = findViewById(R.id.edtCardHolder);
        edtDate = findViewById(R.id.edtDate);
        edtCVC = findViewById(R.id.edtCVC);
        edtNomial = findViewById(R.id.edtNomial);

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
                String nomial = edtNomial.getText().toString().trim();
                Logger.log("cardNumber", cardNumber);
                Logger.log("cardHolder", cardHolder);
                Logger.log("date", date);
                Logger.log("cvc", cvc);
                Logger.log("nomial", nomial);
            }
        });
    }
}