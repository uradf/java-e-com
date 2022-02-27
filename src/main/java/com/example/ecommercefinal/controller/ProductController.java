package com.example.ecommercefinal.controller;

import com.example.ecommercefinal.response.ProductResponse;
import com.example.ecommercefinal.response.SingleProductResponse;
import com.example.ecommercefinal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    final int SIZE = 5;
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public SingleProductResponse getProductById(@RequestParam(defaultValue = "0") int id) {
        return new SingleProductResponse("products/{page}", productService.getById(id));
    }

    @GetMapping("/products")
    public ProductResponse getProductsByPage(@RequestParam(defaultValue = "0") int page) {
        Pageable paging = PageRequest.of(page, SIZE);
        return new ProductResponse("products/{page}", productService.getProductsByPage(paging));
    }

    @GetMapping("/products/by")
    public ProductResponse getProductsByName(@RequestParam(defaultValue = "0") int page, @RequestParam String name) {
        Pageable paging = PageRequest.of(page, SIZE);
        return new ProductResponse("products/{name}", productService.getProductsByName(name, paging));
    }

}
