package com.example.ecommercefinal.response;

import com.example.ecommercefinal.entity.Product;

public class SingleProductResponse {
    private String msg;
    private Product product;

    public SingleProductResponse(String msg, Product product) {
        this.msg = msg;
        this.product = product;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
