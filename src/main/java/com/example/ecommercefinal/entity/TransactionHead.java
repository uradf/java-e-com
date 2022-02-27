package com.example.ecommercefinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "transaction_head")
public class TransactionHead {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int customer;
    private String createat;

    @OneToMany(mappedBy = "transactionHead", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("transactionHead")
    private List<TransactionDetail> transactionDetails;
}
