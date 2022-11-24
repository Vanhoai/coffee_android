package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.models.Product.Product;
import com.example.coffee.screens.bottom.Gift.GiftFragment;
import com.example.coffee.screens.bottom.Product.ProductDetailActivity;

import java.util.ArrayList;

public class RecycleProductDetailAdapter extends RecyclerView.Adapter<RecycleProductDetailAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> products;

    public RecycleProductDetailAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_product_detail_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvProductTitle.setText(product.getName());
        holder.tvProductPrice.setText(String.valueOf(product.getPrice()));
        Glide.with(context).load(product.getImage()).into(holder.imageProduct);
        holder.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.btnCurrent.setText(String.valueOf(product.getCurrent()));
        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product.getCurrent() ==0){
                    return;
                }
                product.setCurrent(product.getCurrent() - 1);
                holder.btnCurrent.setText(String.valueOf(product.getCurrent()));
            }
        });
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setCurrent(product.getCurrent() + 1);
                holder.btnCurrent.setText(String.valueOf(product.getCurrent()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardProduct;
        TextView tvProductTitle;
        TextView tvProductPrice;
        ImageView imageProduct;
        AppCompatButton btnIncrease;
        AppCompatButton btnDecrease;
        AppCompatButton btnCurrent;


        public ViewHolder(@NonNull View view) {
            super(view);
            cardProduct = view.findViewById(R.id.cardProduct);
            tvProductTitle = view.findViewById(R.id.tvProductTitle);
            tvProductPrice = view.findViewById(R.id.tvProductPrice);
            imageProduct = view.findViewById(R.id.imageProduct);
            btnIncrease = view.findViewById(R.id.btnIncrease);
            btnDecrease = view.findViewById(R.id.btnDecrease);
            btnCurrent = view.findViewById(R.id.btnCurrent);
        }
    }

}
