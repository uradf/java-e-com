package com.example.ecommercefinal.entity;

import lombok.Data;

@Data
public class AddRemoveProductInput {
    private int product;
    private int customer;
    private String token;
}
