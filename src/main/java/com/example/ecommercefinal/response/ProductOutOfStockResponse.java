package com.example.ecommercefinal.response;

import lombok.Data;

@Data
public class ProductOutOfStockResponse {
    private String msg;

    public ProductOutOfStockResponse(String msg) {
        this.msg = msg;
    }
}
