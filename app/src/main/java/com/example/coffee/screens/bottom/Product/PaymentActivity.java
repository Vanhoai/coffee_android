package com.example.coffee.screens.bottom.Product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.screens.bottom.MainActivity;

public class PaymentActivity extends AppCompatActivity {

    private AppCompatButton btnPayNow;
    private ImageView backNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // mapping
        initView();

        // handle click
        handleClick();
    }

    private void handleClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, CheckOutActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show dialog success
                showDialog();
            }
        });
    }

    private void initView() {
        btnPayNow  = findViewById(R.id.btnPayNow);
        backNavigation = findViewById(R.id.backNavigation);
    }

    public void showDialog()  {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_successfully, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        AppCompatButton btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}