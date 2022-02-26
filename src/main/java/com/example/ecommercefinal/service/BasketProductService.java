package com.example.ecommercefinal.service;

import com.example.ecommercefinal.config.JwtTokenUtil;
import com.example.ecommercefinal.entity.BasketProduct;
import com.example.ecommercefinal.entity.CustomerBasket;
import com.example.ecommercefinal.entity.ProductBasket;
import com.example.ecommercefinal.exception.CustomerNotFoundException;
import com.example.ecommercefinal.exception.NoDataInBasketException;
import com.example.ecommercefinal.repository.BasketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketProductService {
    @Autowired
    BasketProductRepository basketProductRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public boolean add(int product, int customer, String token) {
        if (!jwtTokenUtil.validate(token)) throw new CustomerNotFoundException();
        if (product == 0 || customer == 0) throw new NoDataInBasketException();

        BasketProduct basketProduct = new BasketProduct();

        ProductBasket productBasket = new ProductBasket();
        productBasket.setId(product);

        CustomerBasket customerBasket = new CustomerBasket();
        customerBasket.setId(customer);

        basketProduct.setProduct(productBasket);
        basketProduct.setCustomer(customerBasket);

        BasketProduct result = basketProductRepository.save(basketProduct);
        if (result.getId() > 0) return true;
        return false;
    }
}
