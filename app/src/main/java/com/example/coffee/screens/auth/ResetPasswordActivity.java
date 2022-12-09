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
import com.example.coffee.interfaces.MailCallback;
import com.example.coffee.models.Others.MailResponse;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.services.AuthService;
import com.example.coffee.utils.LayoutLoading;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.Validation;

import java.util.Random;

public class ResetPasswordActivity extends AppCompatActivity {

    private ImageView backNavigation;
    private ImageView checkEmail, checkNewPassword, checkConfirmNewPassword;
    private EditText edtEmail, edtNewPassword, edtConfirmNewPassword;
    private AppCompatButton btnReset;
    private AuthService authService;
    private ConstraintLayout constraintLayout;
    private LayoutLoading layoutLoading;
    private String code = "";

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

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "DATA IS NOT EMPTY", Toast.LENGTH_SHORT).show();
                    return;
                };
                if (!password.equals(confirmPassword) || !Validation.verifyLogin(email, password)) {
                    Toast.makeText(ResetPasswordActivity.this, "DATA IN VALID", Toast.LENGTH_SHORT).show();
                    return;
                };

                code = randomString();

                Logger.log("CODE", code);

                layoutLoading.setLoading();
                authService.sendCode(email, code, mailCallback);
            }
        });
    }

    private final MailCallback mailCallback = new MailCallback() {
        @Override
        public void onSuccess(MailResponse mailResponse) {
            layoutLoading.setGone();
            Logger.log("RESPONSE", mailResponse);

            String email = edtEmail.getText().toString().trim();
            String password = edtNewPassword.getText().toString().trim();
            Intent intent = new Intent(ResetPasswordActivity.this, SendCodeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            bundle.putString("password", password);
            bundle.putString("code", code);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFailed(boolean failed) {
            layoutLoading.setGone();
            Logger.log("RESPONSE", "ERROR");
        }
    };

    public String randomString() {
        String str = "0123456789";
        StringBuilder res = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index = (int)(str.length() * Math.random());
            res.append(str.charAt(index));
        }

        return res.toString();
    }

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