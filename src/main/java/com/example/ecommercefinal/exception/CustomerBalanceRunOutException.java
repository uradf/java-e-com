package com.example.ecommercefinal.exception;

public class CustomerBalanceRunOutException extends RuntimeException{
    public CustomerBalanceRunOutException(String msg) {
        super(msg);
    }
}
