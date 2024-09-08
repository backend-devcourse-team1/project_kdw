package org.programers.grids_and_circles.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.programers.grids_and_circles.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
@SpringBootTest
@ActiveProfiles(value = "test")
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ProductRepositoryTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    @DisplayName(value = "전체 상품 조회 테스트")
    public void findAllProductTest() {
        //Given
        Products products = Products.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .productName("product")
                .price(1000L)
                .category("dfdf")
                .build();
        productsRepository.save(products);
        //When
        List<Products> all = productsRepository.findAll();

        //Then
        assertThat(all.isEmpty(), is(false));
    }

    @AfterAll
    public void cleanUp() {
        productsRepository.deleteAll();
    }
}