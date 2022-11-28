package com.example.coffee.fcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.models.User.User;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.services.UserService;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.Storage;
import com.example.coffee.utils.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.messaging.FirebaseMessaging;

public class UseFCM {

    public static void getDeviceToken(Context context) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        String token = task.getResult();
                        SharedPreferences sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("deviceToken", token);
                        editor.apply();
                        Logger.log("DEVICE TOKEN", token);

                        updateTokenForUser(token, context);
                    }
                });
    }

    public static String getMyToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("deviceToken", "");
    }

    public static void updateTokenForUser(String token, Context context) {
        User user = UserInformation.getUser(context);
        UserService userService = new UserService();
        userService.updateDeviceToken(user.getId(), token, authCallback);
    }

    private static final AuthCallback authCallback = new AuthCallback() {
        @Override
        public void onSuccess(Boolean value, UserResponse userResponse) {
            Logger.log("AUTH RESPONSE", userResponse);
        }

        @Override
        public void onFailed(Boolean value) {
            Logger.log("RESPONSE", "ERROR");
        }
    };
}
