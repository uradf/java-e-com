package com.example.ecommercefinal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "transaction_detail")

public class TransactionDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int product;
    private String name;
    private int price;
    private int amount;
    private int head;

    @ManyToOne
    @JoinColumn(name="head", nullable = false, insertable = false, updatable = false)
    private TransactionHead transactionHead;
}
