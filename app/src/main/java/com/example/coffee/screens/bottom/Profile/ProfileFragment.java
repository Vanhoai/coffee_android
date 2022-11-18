package com.example.coffee.screens.bottom.Profile;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.auth.LoginActivity;
import com.example.coffee.screens.bottom.Product.PaymentActivity;
import com.example.coffee.screens.bottom.Product.ProductListActivity;
import com.example.coffee.utils.UserInformation;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    private Context context;

    LinearLayout linearAccount, linearHistory, linearPayment, linearBookmark, linearLogout;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getLayoutInflater();
        View view =  layoutInflater.inflate(R.layout.profile_fragment, container, false);

        TextView tvUsername = view.findViewById(R.id.tvUsername);
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        ImageView imageAvatar = view.findViewById(R.id.imageAvatar);

        linearAccount = view.findViewById(R.id.linearAccount);
        linearHistory = view.findViewById(R.id.linearHistory);
        linearPayment = view.findViewById(R.id.linearPayment);
        linearBookmark = view.findViewById(R.id.linearBookmark);
        linearLogout = view.findViewById(R.id.linearLogout);

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

        linearAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AccountActivity.class));
                requireActivity().finish();
            }
        });

        linearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
                requireActivity().finish();
            }
        });

        linearPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), PaymentActivity.class));
//                requireActivity().finish();
            }
        });

        linearBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProductListActivity.class));
                requireActivity().finish();
            }
        });

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
                requireActivity().finish();
            }
        });
    }
}
