package com.example.ecommercefinal.response;

import com.example.ecommercefinal.entity.PickedProduct;
import lombok.Data;

import java.util.List;

@Data
public class BasketProductResponse {
    private String msg;
    private List<PickedProduct> productList;

    public BasketProductResponse(String msg, List<PickedProduct> productList) {
        this.msg = msg;
        this.productList = productList;
    }
}
