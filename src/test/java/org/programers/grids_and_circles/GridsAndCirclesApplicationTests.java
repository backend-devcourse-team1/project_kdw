package org.programers.grids_and_circles;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.programers.grids_and_circles.entity.Products;
import org.programers.grids_and_circles.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class GridsAndCirclesApplicationTests {
    @Autowired
    ProductsRepository productsRepository;

    @Test
    void contextLoads() {
        Products products1 = Products.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .productName("똥")
                .price(1000L)
                .category("똥")
                .description("똥")
                .build();
        Products products2 = Products.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .productName("오줌")
                .price(1000L)
                .category("오줌")
                .description("오줌")
                .build();

        productsRepository.save(products2);
        productsRepository.save(products1);
    }

}
