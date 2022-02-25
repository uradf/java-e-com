package com.example.ecommercefinal.controller;

import com.example.ecommercefinal.exception.AuthenFailedException;
import com.example.ecommercefinal.exception.CustomerNotFoundException;
import com.example.ecommercefinal.response.AuthenFailedResponse;
import com.example.ecommercefinal.response.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomerControllerAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomerResponse userWrongInput(CustomerNotFoundException e) {
        return new CustomerResponse(HttpStatus.NOT_FOUND + "", e.getMessage());
    }

    @ExceptionHandler(AuthenFailedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AuthenFailedResponse userWrongInput(AuthenFailedException e) {
        return new AuthenFailedResponse(HttpStatus.NOT_FOUND + "");
    }
}
