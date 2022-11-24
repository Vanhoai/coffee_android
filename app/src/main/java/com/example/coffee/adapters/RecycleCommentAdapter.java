package com.example.coffee.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.models.Product.Comment;
import com.example.coffee.models.User.User;

import java.util.ArrayList;

public class RecycleCommentAdapter extends RecyclerView.Adapter<RecycleCommentAdapter.ViewHolder> {
    ArrayList<Comment> comments;
    Context context;

    public RecycleCommentAdapter(ArrayList<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.card_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        User user = comment.getUser();
        holder.tvNameUser.setText(user.getUsername());
        holder.tvEmail.setText(user.getEmail());
        String[] desc = comment.getContent().split(" ");
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
            holder.tvDescription.setText(comment.getContent());
        }
//        holder.tvDate.setText(String.valueOf(comment.getCreatedAt()));
        holder.tvRating.setText(String.valueOf(comment.getRating()));
        Glide.with(context).load(user.getImage()).into(holder.imageUser);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageUser;
        TextView tvNameUser;
        TextView tvEmail;
        TextView tvDescription;
        TextView tvDate;
        TextView tvRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageUser = itemView.findViewById(R.id.imageUserComment);
            tvNameUser = itemView.findViewById(R.id.tvNameUser);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
