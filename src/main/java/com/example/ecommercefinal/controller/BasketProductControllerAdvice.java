package com.example.ecommercefinal.controller;

import com.example.ecommercefinal.exception.CustomerNotFoundException;
import com.example.ecommercefinal.exception.NoDataInBasketException;
import com.example.ecommercefinal.response.AddRemoveBasketProductResponse;
import com.example.ecommercefinal.response.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BasketProductControllerAdvice {
    @ExceptionHandler(NoDataInBasketException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AddRemoveBasketProductResponse userWrongInput(CustomerNotFoundException e) {
        return new AddRemoveBasketProductResponse(HttpStatus.NOT_FOUND + "", false);
    }
}
