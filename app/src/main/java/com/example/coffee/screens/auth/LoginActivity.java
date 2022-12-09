package com.example.coffee.screens.auth;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffee.R;
import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.fcm.UseFCM;
import com.example.coffee.models.User.User;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.services.AuthService;
import com.example.coffee.utils.LayoutLoading;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.Storage;
import com.example.coffee.utils.UserInformation;
import com.example.coffee.utils.Validation;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private AppCompatButton btnLogin, btnCreateAccount;
    private AuthService authService;
    private LayoutLoading layoutLoading;
    private ConstraintLayout constraintLayout;
    private ImageView checkEmail, checkPassword;
    private CheckBox checkLogin;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init view
        initView();

        // init service
        authService = new AuthService();

        // handle onclick
        handleOnclick();

        // handle change
        handleOnChange();
    }

    private void handleOnChange() {
        edtEmail.addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                validate(value, "EMAIL");
            }
        }));

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                validate(value, "PASSWORD");
            }
        });
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
            default:
                break;
        }
    }

    private void handleOnclick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                layoutLoading.setLoading();
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

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        constraintLayout = findViewById(R.id.loading);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        layoutLoading = new LayoutLoading(constraintLayout,LoginActivity.this);
        checkEmail = findViewById(R.id.checkEmail);
        checkPassword = findViewById(R.id.checkPassword);
        checkLogin = findViewById(R.id.checkLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
    }

    private final AuthCallback authCallback = new AuthCallback() {
        @Override
        public void onSuccess(Boolean value, UserResponse userResponse) {
            // set gone loading
            layoutLoading.setGone();

            // handle save user
            User user = userResponse.getUser();
            if (saveUserToShareReference(user)) {
                Toast.makeText(LoginActivity.this, "SAVE USER SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "SAVE USER FAILED", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            boolean check = true;
            Bundle bundle = new Bundle();
            bundle.putBoolean("CHECK_REGISTER", check);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFailed(Boolean value) {
            layoutLoading.setGone();
            Toast.makeText(LoginActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
        }
    };

    public boolean saveUserToShareReference(User user) {
        if (checkLogin.isChecked()) {
            SharedPreferences sharedPreferences = getSharedPreferences("CHECK_LOGIN", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("SIGN_IN", true);
            editor.apply();
        }
        return UserInformation.setUser(LoginActivity.this, user);
    }
}