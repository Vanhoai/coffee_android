package com.example.coffee.screens.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coffee.R;
import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.services.AuthService;
import com.example.coffee.utils.LayoutLoading;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.Validation;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtUsername, edtEmail, edtPassword, edtConfirmPassword;
    private AppCompatButton btnCreateAccount;
    private AuthService authService;
    private ImageView backNavigation, checkEmail, checkPassword, checkUsername, checkConfirmPassword;
    private ConstraintLayout constraintLayout;
    private LayoutLoading layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // init view
        initView();

        // init Service
        authService = new AuthService();

        // handle onclick
        handleOnclick();

        // handle onchange
        handleOnchange();
    }

    private void handleOnchange() {
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                validate(value, "EMAIL");
            }
        });

        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                validate(value, "USERNAME");
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                validate(value, "PASSWORD");
            }
        });

        edtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                validate(value, "CONFIRM");
            }
        });
    }

    private void handleOnclick() {
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
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (Validation.verifyRegister(username, email, password, confirmPassword)) {
            Intent intent = new Intent(RegisterActivity.this, VerityActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putString("email", email);
            bundle.putString("password", password);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "DATA INSIDE IS INVALID", Toast.LENGTH_SHORT).show();
        }
    }

    private void validate(String value, String type) {
        Logger.log("VALUE", value);
        switch (type) {
            case "EMAIL": {
                if (Validation.verifyEmail(value)) {
                    checkEmail.setImageResource(R.drawable.check_active);
                } else {
                    checkEmail.setImageResource(R.drawable.check);
                }
                break;
            }
            case "PASSWORD": {
                if (value.length() > 0) {
                    checkPassword.setImageResource(R.drawable.check_active);
                } else {
                    checkPassword.setImageResource(R.drawable.check);
                }
                break;
            }
            case "USERNAME": {
                if (value.length() > 6) {
                    checkUsername.setImageResource(R.drawable.check_active);
                } else {
                    checkUsername.setImageResource(R.drawable.check);
                }
                break;
            }
            case "CONFIRM": {
                if (edtPassword.getText().toString().trim().equals(value)) {
                    checkConfirmPassword.setImageResource(R.drawable.check_active);
                } else {
                    checkConfirmPassword.setImageResource(R.drawable.check);
                }
            }
            default:
                break;
        }
    }

    public void initView() {
        backNavigation = findViewById(R.id.backNavigation);
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        checkEmail = findViewById(R.id.checkEmail);
        checkUsername = findViewById(R.id.checkUsername);
        checkPassword = findViewById(R.id.checkPassword);
        checkConfirmPassword = findViewById(R.id.checkConfirmPassword);
        constraintLayout = findViewById(R.id.loading);
        layoutLoading = new LayoutLoading(constraintLayout, RegisterActivity.this);
        layoutLoading.setGone();
    }
}