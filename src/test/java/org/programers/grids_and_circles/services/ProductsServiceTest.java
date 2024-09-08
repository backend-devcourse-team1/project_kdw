package org.programers.grids_and_circles.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.programers.grids_and_circles.dto.products.ResponseProductDto;
import org.programers.grids_and_circles.entity.Products;
import org.programers.grids_and_circles.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@Slf4j
@ActiveProfiles(value = "test")
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ProductsServiceTest {

    @Autowired
    private ProductsRepository repository;
    @Autowired
    private ProductsService productsService;
    private Products products;
    private UUID uuid;
    @BeforeAll
    public void setUp() {
        products = Products.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .productName("product")
                .price(1000L)
                .category("dfdf")
                .build();

        Products save = repository.save(products);
        uuid = save.getProductId();
    }
    @AfterAll
    public void cleanUp() {
        repository.deleteAll();
    }
    @Test
    void readAllProduct() {
        //Given

        //When
        List<ResponseProductDto> responseProductDtos = productsService.readAllProduct();

        //Then
        assertThat(responseProductDtos.isEmpty(), is(false));

    }

    @Test
    void readProductByUUID() {
        //Given
        //When
        Optional<Products> products1 = productsService.readProductByUUID(uuid);

        assertThat(products1.isPresent(), is(true));
    }
}