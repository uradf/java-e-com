package com.example.ecommercefinal.repository;

import com.example.ecommercefinal.entity.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Integer> {

}
