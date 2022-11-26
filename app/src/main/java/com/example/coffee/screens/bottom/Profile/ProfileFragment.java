package com.example.coffee.screens.bottom.Profile;

import static android.app.Activity.RESULT_OK;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.callbacks.AuthCallback;
import com.example.coffee.models.User.User;
import com.example.coffee.models.User.UserResponse;
import com.example.coffee.screens.auth.LoginActivity;
import com.example.coffee.screens.bottom.Home.CardActivity;
import com.example.coffee.screens.bottom.Product.ProductListActivity;
import com.example.coffee.services.UserService;
import com.example.coffee.utils.LayoutLoading;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.RealPathUtil;
import com.example.coffee.utils.UserInformation;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileFragment extends Fragment {
    private Context context;

    private static final int REQUEST_CODE = 10;
    private static final String TAG = ProfileFragment.class.getName();

    LinearLayout linearAccount, linearHistory, linearPayment, linearBookmark, linearLogout;
    ImageView imageUploadAvatar;
    ImageView imageAvatar;
    TextView tvUsername;
    TextView tvEmail;
    UserService userService;
    AppCompatButton btnChange;
    LayoutLoading layoutLoading;
    Uri mUri;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d(TAG, "On Activity Result");
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                            imageAvatar.setImageBitmap(bitmap);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
    );

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getLayoutInflater();
        View view =  layoutInflater.inflate(R.layout.profile_fragment, container, false);

        // init
        init(view);

        // init domain
        userService = new UserService();

        // request permission read in gallery
        requestPermission();

        // get data
        User user = UserInformation.getUser(getContext());

        // set view
        tvUsername.setText(user.getUsername());
        tvEmail.setText(user.getEmail());
        Glide.with(requireContext()).load(user.getImage()).into(imageAvatar);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handleOnclick();
    }

    private void init(View view) {
        tvUsername = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);
        imageAvatar = view.findViewById(R.id.imageAvatar);
        linearAccount = view.findViewById(R.id.linearAccount);
        linearHistory = view.findViewById(R.id.linearHistory);
        linearPayment = view.findViewById(R.id.linearPayment);
        linearBookmark = view.findViewById(R.id.linearBookmark);
        linearLogout = view.findViewById(R.id.linearLogout);
        imageUploadAvatar = view.findViewById(R.id.imageUploadAvatar);
        btnChange = view.findViewById(R.id.btnUpload);

        // add loading
        ConstraintLayout constraintLayout = view.findViewById(R.id.loading);
        layoutLoading = new LayoutLoading(constraintLayout, requireContext());
    }

    private void handleOnclick() {

        // upload file
        imageUploadAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadAvatar();
            }
        });

        linearAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AccountActivity.class);
                requireContext().startActivity(intent);
                requireActivity().finish();
            }
        });

        linearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), HistoryActivity.class);
                requireContext().startActivity(intent);
                requireActivity().finish();
            }
        });

        linearPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CardActivity.class);
                requireContext().startActivity(intent);
                requireActivity().finish();
            }
        });

        linearBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProductListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Page Title", "Bookmark");
                intent.putExtras(bundle);
                requireContext().startActivity(intent);
                requireActivity().finish();
            }
        });

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                requireContext().startActivity(intent);
                requireActivity().finish();
            }
        });
    }

    private void uploadAvatar() {
        String path = RealPathUtil.getRealPath(requireContext(), mUri);

        // get data
        User user = UserInformation.getUser(requireContext());
        File file = new File(path);

        LayoutLoading.setLoading();
        userService.uploadAvatar(user.getAccessToken(), user.getId(), file, new AuthCallback() {
            @Override
            public void onSuccess(Boolean value, UserResponse userResponse) {
                Logger.log(TAG, userResponse);
                LayoutLoading.setGone();
            }

            @Override
            public void onFailed(Boolean value) {
                LayoutLoading.setGone();
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        if (requireContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
        } else {
            String[] permission = { Manifest.permission.READ_EXTERNAL_STORAGE };
            requestPermissions(permission, REQUEST_CODE);
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Logger.log("PERMISSION", "GRANTED");
            } else {
                Toast.makeText(requireContext(), "NOT PERMISSION", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
