package com.example.ecommercefinal.service;

import com.example.ecommercefinal.config.JwtTokenUtil;
import com.example.ecommercefinal.entity.BasketProduct;
import com.example.ecommercefinal.entity.CustomerBasket;
import com.example.ecommercefinal.entity.PickedProduct;
import com.example.ecommercefinal.entity.ProductBasket;
import com.example.ecommercefinal.exception.NoDataInBasketException;
import com.example.ecommercefinal.repository.BasketProductRepository;
import com.example.ecommercefinal.repository.PickedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketProductService {
    @Autowired
    BasketProductRepository basketProductRepository;

    @Autowired
    PickedProductRepository pickedProductRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public void setBasketProductRepository(BasketProductRepository basketProductRepository) {
        this.basketProductRepository = basketProductRepository;
    }

    public void setPickedProductRepository(PickedProductRepository pickedProductRepository) {
        this.pickedProductRepository = pickedProductRepository;
    }

    public List<PickedProduct> get(String token) {
        if (!jwtTokenUtil.validate(token)) return new ArrayList<PickedProduct>();
        int customer = jwtTokenUtil.getUserId(token);
        return pickedProductRepository.getByCustomer(customer);
    }

    public boolean add(int product, int customer, String token) {
        if (!jwtTokenUtil.validate(token)) return false;
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

    public boolean remove(int product, int customer, String token) {
        if (!jwtTokenUtil.validate(token)) return false;
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
