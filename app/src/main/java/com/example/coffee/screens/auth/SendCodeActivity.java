package com.example.coffee.screens.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
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

public class SendCodeActivity extends AppCompatActivity {

    private EditText edtCode;
    private AuthService authService;
    private AppCompatButton btnConfirm;
    private LayoutLoading layoutLoading;
    private ImageView backNavigation;
    private String email = "", password = "", code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);

        initView();

        getData();

        handleOnClick();

        authService = new AuthService();
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        email = bundle.getString("email", "");
        password = bundle.getString("password", "");
        code = bundle.getString("code", "");

        edtCode.setText(code);
    }

    private void handleOnClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendCodeActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeConfirm = edtCode.getText().toString().trim();

                Logger.log("CODE", code);
                Logger.log("CONFIRM", codeConfirm);

                if (codeConfirm.equals(code)) {
                    layoutLoading.setLoading();
                    authService.resetPassword(email, password, authCallback);
                    return;
                }

                Toast.makeText(SendCodeActivity.this, "CODE INVALID", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        edtCode = findViewById(R.id.edtCode);
        btnConfirm = findViewById(R.id.btnConfirm);
        backNavigation = findViewById(R.id.backNavigation);
        ConstraintLayout constraintLayout = findViewById(R.id.loading);
        layoutLoading = new LayoutLoading(constraintLayout, SendCodeActivity.this);
        layoutLoading.setGone();
    }



    private final AuthCallback authCallback = new AuthCallback() {
        @Override
        public void onSuccess(Boolean value, UserResponse userResponse) {
            layoutLoading.setGone();
            Logger.log("USER RESPONSE", userResponse);
            Toast.makeText(SendCodeActivity.this, "RESET PASSWORD SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SendCodeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFailed(Boolean value) {
            layoutLoading.setGone();
            Toast.makeText(SendCodeActivity.this, "EMAIL NOT FOUND", Toast.LENGTH_SHORT).show();
        }
    };
}