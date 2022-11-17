package com.example.coffee.screens.bottom.Profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coffee.R;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.auth.LoginActivity;
import com.example.coffee.screens.bottom.Product.PaymentActivity;
import com.example.coffee.screens.bottom.Product.ProductListActivity;
import com.example.coffee.utils.UserInformation;

public class ProfileFragment extends Fragment {
    TextView tvAccount, tvHistory, tvPayment, tvBookmark, tvLogout, tvUser, tvEmail;
    RelativeLayout relativeImage;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view =  layoutInflater.inflate(R.layout.profile_fragment, container, false);

        tvAccount = view.findViewById(R.id.tvAccount);
        tvHistory = view.findViewById(R.id.tvHistory);
        tvPayment = view.findViewById(R.id.tvPayment);
        tvBookmark = view.findViewById(R.id.tvBookmark);
        tvLogout = view.findViewById(R.id.tvLogout);
        tvUser = view.findViewById(R.id.tvUser);
        tvEmail = view.findViewById(R.id.tvEmail);
        relativeImage = view.findViewById(R.id.relativeImage);

        User user = UserInformation.getUser(getContext());
        tvUser.setText(user.getUsername());
        tvEmail.setText(user.getEmail());
        user.getImage();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AccountActivity.class));
                requireActivity().finish();
            }
        });

        tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
                requireActivity().finish();
            }
        });

        tvPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PaymentActivity.class));
                requireActivity().finish();
            }
        });

        tvBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProductListActivity.class));
                requireActivity().finish();
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
                requireActivity().finish();
            }
        });
    }
}
