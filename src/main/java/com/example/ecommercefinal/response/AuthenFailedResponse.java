package com.example.ecommercefinal.response;

import lombok.Data;

@Data
public class AuthenFailedResponse {
    private String msg;

    public AuthenFailedResponse(String msg) {
        this.msg = msg;
    }
}
