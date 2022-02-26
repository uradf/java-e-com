package com.example.ecommercefinal.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
@Data
public class PickedProduct {

    @Id
    private int id;
    private String name;
    private int price;
}
