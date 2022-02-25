package com.example.ecommercefinal;

import com.example.ecommercefinal.entity.Product;
import com.example.ecommercefinal.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class IntegrationTest {

    final int SIZE = 5;

    @Autowired
    ProductRepository productRepository;

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
}
