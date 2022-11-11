package com.example.coffee.screens.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.coffee.R;

public class VerityActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtPhone;
    ImageView checkPhone;
    ImageView backNavigation;
    AppCompatButton btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verity);

        edtPhone = findViewById(R.id.edtPhone);
        checkPhone = findViewById(R.id.checkPhone);
        btnVerify = findViewById(R.id.btnVerify);
        backNavigation = findViewById(R.id.backNavigation);

        btnVerify.setOnClickListener(this);
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerityActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        // handle verify
    }
}