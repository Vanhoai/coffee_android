package com.example.coffee.screens.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class ResetPasswordActivity extends AppCompatActivity {

    private ImageView backNavigation;
    private ImageView checkEmail, checkNewPassword, checkConfirmNewPassword;
    private EditText edtEmail, edtNewPassword, edtConfirmNewPassword;
    private AppCompatButton btnReset;
    private AuthService authService;
    private ConstraintLayout constraintLayout;
    private LayoutLoading layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // init view
        initView();

        // init service
        authService = new AuthService();

        // handle click
        handleClick();

        // handle change
        handleChange();
    }

    private void handleChange() {
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

        edtNewPassword.addTextChangedListener(new TextWatcher() {
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

        edtConfirmNewPassword.addTextChangedListener(new TextWatcher() {
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

    private void handleClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtNewPassword.getText().toString().trim();
                String confirmPassword = edtConfirmNewPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) return;
                if (!password.equals(confirmPassword) || !Validation.verifyLogin(email, password)) return;

                layoutLoading.setLoading();
                authService.resetPassword(email, password, authCallback);
            }
        });
    }

    private final AuthCallback authCallback = new AuthCallback() {
        @Override
        public void onSuccess(Boolean value, UserResponse userResponse) {
            layoutLoading.setGone();
            Logger.log("USER RESPONSE", userResponse);
            Toast.makeText(ResetPasswordActivity.this, "RESET PASSWORD SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFailed(Boolean value) {
            layoutLoading.setGone();
            Toast.makeText(ResetPasswordActivity.this, "EMAIL NOT FOUND", Toast.LENGTH_SHORT).show();
        }
    };

    private void validate(String value, String type) {
        Logger.log("VALUE", value);
        switch (type) {
            case "EMAIL": {
                if (Validation.verifyEmail(value)) {
                    checkEmail.setImageResource(R.drawable.check_active);
                    break;
                }
                checkEmail.setImageResource(R.drawable.check);
                break;
            }
            case "PASSWORD": {
                if (value.length() > 0) {
                    checkNewPassword.setImageResource(R.drawable.check_active);
                    break;
                }
                checkNewPassword.setImageResource(R.drawable.check);
                break;
            }
            case "CONFIRM": {
                if (edtNewPassword.getText().toString().trim().equals(value)) {
                    checkConfirmNewPassword.setImageResource(R.drawable.check_active);
                    break;
                }
                checkConfirmNewPassword.setImageResource(R.drawable.check);
                break;
            }
        }
    }

    private void initView() {
        backNavigation = findViewById(R.id.backNavigation);
        checkEmail = findViewById(R.id.checkEmail);
        checkNewPassword = findViewById(R.id.checkNewPassword);
        checkConfirmNewPassword = findViewById(R.id.checkConfirmNewPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmNewPassword = findViewById(R.id.edtConfirmNewPassword);
        btnReset = findViewById(R.id.btnReset);
        constraintLayout = findViewById(R.id.loading);
        layoutLoading = new LayoutLoading(constraintLayout, ResetPasswordActivity.this);
        layoutLoading.setGone();
    }
}