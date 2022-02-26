package com.example.ecommercefinal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "Product")
public class ProductBasket {
    @Id
    private int id;

    @OneToMany(mappedBy = "product")
    List<BasketProduct> basketProducts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
