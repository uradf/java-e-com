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
}
