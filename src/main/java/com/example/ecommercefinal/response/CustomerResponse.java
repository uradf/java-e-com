package com.example.ecommercefinal.response;

import lombok.Data;

@Data

public class CustomerResponse {
    private String msg;
    private String token;

    public CustomerResponse(String msg, String token) {
        this.msg = msg;
        this.token = token;
    }
}
