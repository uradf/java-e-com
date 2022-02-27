package com.example.ecommercefinal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BasketProduct {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product")
    ProductBasket product;

    @ManyToOne
    @JoinColumn(name = "customer")
    CustomerBasket customer;
}
