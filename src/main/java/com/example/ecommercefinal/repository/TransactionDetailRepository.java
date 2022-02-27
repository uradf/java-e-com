package com.example.ecommercefinal.repository;

import com.example.ecommercefinal.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
}
