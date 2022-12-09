package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.ContentInfo;
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
import com.example.coffee.models.Product.ProductDetail;
import com.example.coffee.screens.bottom.Gift.GiftFragment;
import com.example.coffee.screens.bottom.Product.ProductDetailActivity;
import com.example.coffee.utils.Logger;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecycleProductDetailAdapter extends RecyclerView.Adapter<RecycleProductDetailAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Product> products;
    private final UpdateTotal updateTotal;
    private final UpdateOrder updateOrder;
    private String status;
    private int shop;
    private int order;
    private OnClick onClick;

    public RecycleProductDetailAdapter(Context context, ArrayList<Product> products, String status, int shop, int order, OnClick onClick,UpdateTotal updateTotal, UpdateOrder updateOrder) {
        this.context = context;
        this.products = products;
        this.updateTotal = updateTotal;
        this.updateOrder = updateOrder;
        this.status = status;
        this.shop = shop;
        this.onClick = onClick;
        if (order != -1) {
            this.order = order;
        } else {
            this.order = -1;
        }
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
        Logger.log("PRODUCT", product);
        holder.tvProductTitle.setText(product.getName());
        holder.tvProductPrice.setText(String.valueOf(product.getPrice()));
        Glide.with(context).load(product.getImage()).into(holder.imageProduct);
        holder.tvProductPrice.setText(String.format("%.1f VND", product.getPrice() * product.getCurrent()));
        holder.btnCurrent.setText(String.valueOf(product.getCurrent()));
        updateTotal.update(products);

        holder.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClick(product);
            }
        });
        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product.getCurrent() ==0){
                    return;
                }

                // update current
                product.setCurrent(product.getCurrent() - 1);
                holder.btnCurrent.setText(String.valueOf(product.getCurrent()));
                holder.tvProductPrice.setText(String.format("%.1f VND", product.getPrice() * product.getCurrent()));
                updateTotal.update(products);
                updateOrder.update(products, product, -1);
            }
        });
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setCurrent(product.getCurrent() + 1);
                holder.btnCurrent.setText(String.valueOf(product.getCurrent()));
                holder.tvProductPrice.setText(String.format("%.1f VND", product.getPrice() * product.getCurrent()));
                updateTotal.update(products);
                updateOrder.update(products, product, 1);
            }
        });
    }

    public interface UpdateTotal {
        public void update(ArrayList<Product> products);
    }

    public interface UpdateOrder {
        public void update(ArrayList<Product> products, Product product, int change);
    }

    public interface OnClick {
        public void onClick(Product product);
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
