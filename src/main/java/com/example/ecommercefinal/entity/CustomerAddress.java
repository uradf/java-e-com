package com.example.ecommercefinal.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Customer")
public class CustomerAddress {
    @Id
    private int id;
    private String province;


}
