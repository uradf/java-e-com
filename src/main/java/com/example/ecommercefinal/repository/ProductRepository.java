package com.example.ecommercefinal.repository;

import com.example.ecommercefinal.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByNameContains(String name, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}
