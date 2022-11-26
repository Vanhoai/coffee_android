package com.example.coffee.models.Product;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductDetailResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private ProductDetail productDetail;

    public ProductDetailResponse(String message, int code, ProductDetail productDetail) {
        super(message, code);
        this.productDetail = productDetail;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    @Override
    public String toString() {
        return "ProductDetailResponse{" +
                "productDetail=" + productDetail +
                '}';
    }
}
