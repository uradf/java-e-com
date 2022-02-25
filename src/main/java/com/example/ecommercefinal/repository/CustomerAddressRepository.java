package com.example.ecommercefinal.repository;

import com.example.ecommercefinal.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {
    Optional<CustomerAddress> findById(int id);
}
