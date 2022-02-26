package com.example.ecommercefinal.controller;

import com.example.ecommercefinal.entity.AddRemoveProductInput;
import com.example.ecommercefinal.response.AddRemoveBasketProductResponse;
import com.example.ecommercefinal.service.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class BasketProductController {

    @Autowired
    BasketProductService basketProductService;

    @PostMapping("/addProduct")
    public AddRemoveBasketProductResponse add(@RequestBody AddRemoveProductInput addProductInput ) {
        return new AddRemoveBasketProductResponse("addProduct", basketProductService.add(addProductInput.getProduct(), addProductInput.getCustomer(), addProductInput.getToken()));
    }

    @PostMapping("/removeProduct")
    public AddRemoveBasketProductResponse remove(@RequestBody AddRemoveProductInput removeProductInput ) {
        return new AddRemoveBasketProductResponse("removeProduct", basketProductService.add(removeProductInput.getProduct(), removeProductInput.getCustomer(), removeProductInput.getToken()));
    }
}
