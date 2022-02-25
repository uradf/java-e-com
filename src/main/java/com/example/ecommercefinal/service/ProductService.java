package com.example.ecommercefinal.service;

import com.example.ecommercefinal.entity.Product;
import com.example.ecommercefinal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> getProductsByName(String name, Pageable pageable) {
        return productRepository.findByNameContains(name, pageable);
    }

    public Page<Product> getProductsByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
