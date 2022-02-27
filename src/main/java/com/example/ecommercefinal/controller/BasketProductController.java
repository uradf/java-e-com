package com.example.ecommercefinal.controller;

import com.example.ecommercefinal.entity.AddRemoveProductInput;
import com.example.ecommercefinal.response.AddRemoveBasketProductResponse;
import com.example.ecommercefinal.response.BasketProductResponse;
import com.example.ecommercefinal.service.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class BasketProductController {

    @Autowired
    BasketProductService basketProductService;

    @GetMapping("/basket")
    public BasketProductResponse get(@RequestParam(defaultValue = "0") String token) {
        return new BasketProductResponse("basket", basketProductService.get(token));
    }

    @PostMapping("/addProduct")
    public AddRemoveBasketProductResponse add(@RequestBody AddRemoveProductInput addProductInput ) {
        return new AddRemoveBasketProductResponse("addProduct", basketProductService.add(addProductInput.getProduct(), addProductInput.getCustomer(), addProductInput.getToken()));
    }

    @PostMapping("/removeProduct")
    public AddRemoveBasketProductResponse remove(@RequestBody AddRemoveProductInput removeProductInput ) {
        return new AddRemoveBasketProductResponse("removeProduct", basketProductService.remove(removeProductInput.getProduct(), removeProductInput.getCustomer(), removeProductInput.getToken()));
    }
}
