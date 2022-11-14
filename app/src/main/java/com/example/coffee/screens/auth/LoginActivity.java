package com.example.coffee.screens.auth;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.coffee.R;
import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.services.AuthService;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtPassword;
    AppCompatButton btnLogin;
    AppCompatButton btnCreateAccount;
    AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // mapping
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        // init service
        authService = new AuthService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                authService.login(email, password, authCallback);
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private final AuthCallback authCallback = new AuthCallback() {
        @Override
        public void onSuccess(Boolean value, UserResponse userResponse) {
            LayoutLoading.setGone();

            // handle save user
            User user = userResponse.getUser();
            if (saveUserToShareReference(user)) {
                Toast.makeText(LoginActivity.this, "SAVE USER SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "SAVE USER FAILED", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFailed(Boolean value) {

        }
    };
    
}