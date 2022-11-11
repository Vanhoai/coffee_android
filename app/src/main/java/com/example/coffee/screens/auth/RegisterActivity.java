package com.example.coffee.screens.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.coffee.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView backNavigation;
    EditText edtUsername;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtConfirmPassword;
    AppCompatButton btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // mapping
        backNavigation = findViewById(R.id.backNavigation);
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // validate


        // create account => verify phone number
        Intent intent = new Intent(RegisterActivity.this, VerityActivity.class);
        startActivity(intent);
        finish();
    }
}