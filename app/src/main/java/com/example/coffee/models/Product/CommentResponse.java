package com.example.coffee.models.Product;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private Comment comment;

    public CommentResponse(String message, int code, Comment comment) {
        super(message, code);
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "comment=" + comment +
                '}';
    }
}
