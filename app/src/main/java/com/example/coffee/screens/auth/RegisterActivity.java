package com.example.coffee.screens.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coffee.R;
import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.services.AuthService;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView backNavigation;
    EditText edtUsername;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtConfirmPassword;
    AppCompatButton btnCreateAccount;
    AuthService authService;

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

        // init service
        authService = new AuthService();
    
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
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "PLEASE ENTER FULL FIELD", Toast.LENGTH_SHORT).show();
        } else if (password.equals(confirmPassword)) {
            register(username, email, password);
        }
        // create account => verify phone number

    }
    public void register(String username, String email, String password){
        try {
            authService.register(username, email, password, new AuthCallback() {
                @Override
                public void onSuccess(Boolean value, UserResponse userResponse) {
                    Log.d("User", userResponse.toString());
                    Intent intent = new Intent(RegisterActivity.this, VerityActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailed(Boolean value) {
                    Toast.makeText(RegisterActivity.this, "REGISTER FAILED", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}