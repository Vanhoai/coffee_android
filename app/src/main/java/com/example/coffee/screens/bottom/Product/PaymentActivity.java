package com.example.coffee.screens.bottom.Product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.coffee.R;
import com.example.coffee.callbacks.OrderCallback;
import com.example.coffee.models.Order.Order;
import com.example.coffee.models.Order.OrderResponse;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.services.OrderService;
import com.example.coffee.utils.Logger;

public class PaymentActivity extends AppCompatActivity {

    private AppCompatButton btnPayNow;
    private ImageView backNavigation;
    private RelativeLayout relativeWallet, relativeZaloPay;
    private RadioButton checkWallet, checkZaloPay;
    private Order order;
    private OrderService orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // init View
        initView();

        // handle click
        handleClick();

        getOrder();

        orderService = new OrderService();
    }

    private void handleClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, CheckOutActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show dialog success
                if (checkWallet.isChecked()) {
                    Logger.log("ORDER", order);
                    orderService.updateStatusOrder(order.getId(), 3, orderCallback);
                    return;
                }

                // handle zalo pay
            }
        });

        relativeWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWallet.setChecked(true);
                checkZaloPay.setChecked(false);
            }
        });

        relativeZaloPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWallet.setChecked(false);
                checkZaloPay.setChecked(true);
            }
        });
    }

    private final OrderCallback orderCallback = new OrderCallback() {
        @Override
        public void onSuccess(boolean value, OrderResponse orderResponse) {
            Logger.log("RESPONSE", orderResponse);
            showDialog();
        }

        @Override
        public void onFailed(boolean value) {

        }
    };

    private void initView() {
        btnPayNow  = findViewById(R.id.btnPayNow);
        backNavigation = findViewById(R.id.backNavigation);
        relativeWallet = findViewById(R.id.relativeWallet);
        relativeZaloPay = findViewById(R.id.relativeZaloPay);
        checkWallet = findViewById(R.id.checkWallet);
        checkZaloPay = findViewById(R.id.checkZaloPay);
    }

    public void getOrder() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            order = (Order) bundle.getSerializable("ORDER");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void showDialog()  {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_successfully, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        AppCompatButton btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}