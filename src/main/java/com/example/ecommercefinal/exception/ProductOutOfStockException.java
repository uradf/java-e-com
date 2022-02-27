package com.example.ecommercefinal.exception;

public class ProductOutOfStockException extends RuntimeException{
    public ProductOutOfStockException(String msg) {
        super(msg);
    }
}
