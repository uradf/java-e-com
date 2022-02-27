package com.example.ecommercefinal.response;

import lombok.Data;

@Data
public class AddRemoveBasketProductResponse {

    private String msg;
    private boolean success;

    public AddRemoveBasketProductResponse(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }
}
