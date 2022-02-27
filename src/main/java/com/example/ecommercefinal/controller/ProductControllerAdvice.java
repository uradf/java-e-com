package com.example.ecommercefinal.controller;

import com.example.ecommercefinal.exception.ProductOutOfStockException;
import com.example.ecommercefinal.response.ProductOutOfStockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductOutOfStockException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ProductOutOfStockResponse outOfStock(ProductOutOfStockException e) {
        return new ProductOutOfStockResponse(e.getMessage());
    }
}
