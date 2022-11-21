package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.models.Product.Product;
import com.example.coffee.services.ProductService;

import java.util.ArrayList;

public class RecycleProductAdapter extends RecyclerView.Adapter<RecycleProductAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> products;

    public RecycleProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        String[] desc = product.getDescription().split(" ");
        if (desc.length > 12) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 12; i++) {
                result.append(desc[i]);
                if (i < 11) {
                    result.append(" ");
                }
            }
            holder.tvDescription.setText(String.format("%s ...", result));
        } else {
            holder.tvDescription.setText(product.getDescription());
        }
        holder.tvRating.setText(String.valueOf(product.getRating()));
        Glide.with(context).load(product.getImage()).into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardProduct;
        ImageView imageProduct;
        TextView tvName;
        TextView tvDescription;
        TextView tvRating;

        public ViewHolder(@NonNull View view) {
            super(view);
            cardProduct = view.findViewById(R.id.cardProduct);
            imageProduct = view.findViewById(R.id.imageProduct);
            tvName = view.findViewById(R.id.tvName);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvRating = view.findViewById(R.id.tvRating);
        }
    }

}
