package com.example.ecommercefinal;

import com.example.ecommercefinal.config.JwtTokenUtil;
import com.example.ecommercefinal.entity.Customer;
import com.example.ecommercefinal.entity.PickedProduct;
import com.example.ecommercefinal.entity.Product;
import com.example.ecommercefinal.entity.TransactionHead;
import com.example.ecommercefinal.repository.*;
import com.example.ecommercefinal.service.BasketProductService;
import com.example.ecommercefinal.service.ProductService;
import com.example.ecommercefinal.service.TransactionService;
import com.example.ecommercefinal.service.WalletGateWay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class IntegrationTest {

    final int SIZE = 5;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BasketProductRepository basketProductRepository;

    @Autowired
    PickedProductRepository pickedProductRepository;

    @Autowired
    TransactionHeadRepository transactionHeadRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    BasketProductService basketProductService;

    TransactionService transactionService;

    WalletGateWay walletGateWay;

    ProductService productService;

    @Test
    @DisplayName("User กดเรียกดูสินค้า โดยระบบจะแสดงสินค้าหน้าละ 5 ชิ้น")
    public void test01() {

        // Arrange
        Pageable paging = PageRequest.of(0, SIZE);

        // Act
        Page<Product> result = productRepository.findAll(paging);

        // Assert
        long count = result.get().count();
        assertEquals(count, SIZE);
    }

    @Test
    @DisplayName("User กดดูสินค้าหน้าที่ 2")
    public void test02() {

        // Arrange
        Pageable paging = PageRequest.of(1, SIZE);

        // Act
        Page<Product> result = productRepository.findAll(paging);

        // Assert
        long count = result.get().count();
        assertEquals(count, SIZE);
    }

    @Test
    @DisplayName("User ไม่เจอสินค้าที่ต้องการ เลยทำการค้นหาสินค้าจากชื่อ โดยใส่คำว่า Submarine")
    public void test03() {

        // Arrange
        Pageable paging = PageRequest.of(0, SIZE);
        String name = "Submarine";

        // Act
        Page<Product> result = productRepository.findByNameContains(name, paging);

        // Assert
        long count = result.get().count();
        assert(count == 1);
    }

    @Test
    @DisplayName("System แสดงสินค้าที่ชื่อว่า Submarine (ชื่อ ราคา คำอธิบาย)")
    public void test04() {

        // Arrange
        Pageable paging = PageRequest.of(0, SIZE);
        String name = "Submarine";
        int tankPrice = 2000000;
        String description = "No tax very Good";

        // Act
        Page<Product> result = productRepository.findByNameContains(name, paging);

        // Assert
        String itemName = result.toList().get(0).getName();
        int itemPrice = result.toList().get(0).getPrice();
        String itemDescription = result.toList().get(0).getDescription();
        assertEquals(itemName, name);
        assertEquals(itemPrice, tankPrice);
        assertEquals(description, itemDescription);

    }

    @Test
    @DisplayName("User ทำการหยิบสินค้าชิ้นนี้ใส่ตะกร้า แต่ระบบบังคับให้เข้าสู่ระบบ")
    public void test05() {
        // Arrange
        String token = "";
        int customer = 1;
        int product = 14;
        boolean RESULT = false;
        basketProductService = new BasketProductService();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        basketProductService.setJwtTokenUtil(jwtTokenUtil);
        basketProductService.setBasketProductRepository(basketProductRepository);

        // Act
        boolean result = basketProductService.add(product, customer, token);

        // Assert
        assertEquals(RESULT, result);
    }

    @Test
    @DisplayName("(User ทำการเข้าระบบ)")
    public void test06() {
        // Arrange
        String userid = "prayut";
        String password = "8b8e9715d12e4ca12c4c3eb4865aaf6a";
        int RESULT = 1;

        // Act
        Optional<Customer> customer = customerRepository.findByUseridAndPassword(userid, password);
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

        String token = jwtTokenUtil.generateAccessToken(customer.get());
        int result = jwtTokenUtil.getUserId(token);
        // Assert
        assertEquals(RESULT, result);
    }

    public String getToken() {
        String userid = "prayut";
        String password = "8b8e9715d12e4ca12c4c3eb4865aaf6a";
        int RESULT = 1;
        Optional<Customer> customer = customerRepository.findByUseridAndPassword(userid, password);
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = jwtTokenUtil.generateAccessToken(customer.get());
        return token;
    }

    @Test
    @DisplayName("User ทำการหยิบสินค้าชิ้นนี้ใส่ตะกร้า")
    public void test07() {
        // Arrange
        String token = getToken();
        int product = 14;
        int customer = 1;
        boolean RESULT = true;
        basketProductService = new BasketProductService();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        basketProductService.setJwtTokenUtil(jwtTokenUtil);
        basketProductService.setBasketProductRepository(basketProductRepository);

        // Act
        boolean result = basketProductService.add(product, customer, token);

        // Assert
        assertEquals(RESULT, result);
    }

    @Test
    @DisplayName("User กดสั่งซื้อสินค้า และ System แสดงรายละเอียดสินค้าในตะกร้า (ชื่อ ราคา)")
    public void test08() {
        // Arrange
        String token = getToken();
        int customer = 1;
        int product = 15;
        String RESULT_NAME = "Submarine";
        int RESULT_PRICE = 2000000;
        boolean RESULT_ADD = true;

        basketProductService = new BasketProductService();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        basketProductService.setJwtTokenUtil(jwtTokenUtil);
        basketProductService.setBasketProductRepository(basketProductRepository);
        basketProductService.setPickedProductRepository(pickedProductRepository);

        // Act
        boolean result = basketProductService.add(product, customer, token);
        List<PickedProduct> pickedProducts = basketProductService.get(token);

        // Assert
        assertEquals(RESULT_ADD, result);
        pickedProducts.forEach(pickedProduct -> {
            assertEquals(RESULT_NAME, pickedProduct.getName());
            assertEquals(RESULT_PRICE, pickedProduct.getPrice());
        });
    }

    @Test
    @DisplayName("User เลือกจ่ายด้วย External Wallet address และ ยืนยันการสั่งซื้อ (System ทำการตัดเงินและ Stock) และระบบจะแสดงใบเสร็จ")
    public void test09() {
        // Arrange
        int FINAL_AMOUNT = 7;
        int product = 15;
        int customer = 1;
        String token = getToken();
        transactionService = new TransactionService();
        basketProductService = new BasketProductService();
        walletGateWay = new WalletGateWay();
        productService = new ProductService();
        basketProductService.setPickedProductRepository(pickedProductRepository);
        basketProductService.setBasketProductRepository(basketProductRepository);
        basketProductService.setJwtTokenUtil(new JwtTokenUtil());
        productService.setProductRepository(productRepository);

        transactionService.setBasketProductService(basketProductService);
        transactionService.setWalletGateWay(walletGateWay);
        transactionService.setProductService(productService);
        transactionService.setJwtTokenUtil(new JwtTokenUtil());
        transactionService.setTransactionHeadRepository(transactionHeadRepository);
        transactionService.setTransactionDetailRepository(transactionDetailRepository);

        // Act
        basketProductService.add(product, customer, token);
        basketProductService.add(product, customer, token);
        basketProductService.add(product, customer, token);
        List<TransactionHead> transactionHeadList = transactionService.createTransaction(token);
        Product _product = productService.getById(product);

        // Assert
        assertEquals(transactionHeadList.get(0).getId(), 1);
        assertEquals(FINAL_AMOUNT, _product.getAmount());
    }

}
