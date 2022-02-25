package com.example.ecommercefinal.controller;

import com.example.ecommercefinal.entity.UserInput;
import com.example.ecommercefinal.response.CustomerResponse;
import com.example.ecommercefinal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/auth")
    public CustomerResponse getToken(@RequestBody UserInput userInput) throws NoSuchAlgorithmException {
        return new CustomerResponse("/auth", customerService.getToken(userInput.getUsername(), userInput.getPassword()));
    }

    @GetMapping("/address")
    public CustomerResponse getUserAddress(@RequestParam(defaultValue = "0") String token)  {
        return new CustomerResponse("/address", customerService.getAddress(token));
    }
}
