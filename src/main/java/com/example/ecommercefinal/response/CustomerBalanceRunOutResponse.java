package com.example.ecommercefinal.response;

import lombok.Data;

@Data
public class CustomerBalanceRunOutResponse {
    private String msg;

    public CustomerBalanceRunOutResponse(String msg) {
        this.msg = msg;
    }
}
