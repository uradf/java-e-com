package com.example.ecommercefinal.repository;

import com.example.ecommercefinal.entity.PickedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PickedProductRepository extends JpaRepository<PickedProduct, Integer> {
    @Query(value = "SELECT p.id, p.name, p.price, p.amount, p.description FROM Product p JOIN basket_product bp ON bp.product = p.id WHERE bp.customer = ?1", nativeQuery = true)
    List<PickedProduct> getByCustomer(int customer);
}
