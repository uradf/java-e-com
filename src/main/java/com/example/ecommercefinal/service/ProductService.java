package com.example.ecommercefinal.service;

import com.example.ecommercefinal.entity.Product;
import com.example.ecommercefinal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public Product getById(int id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    public boolean decreaseStock(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) return false;
        if (product.get().getAmount() == 0) return false;
        product.get().setAmount(product.get().getAmount() - 1);
        productRepository.save(product.get());
        return true;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
