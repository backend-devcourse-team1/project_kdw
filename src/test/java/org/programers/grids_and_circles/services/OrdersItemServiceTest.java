package org.programers.grids_and_circles.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.programers.grids_and_circles.dto.orderItem.OrderItemRequestDto;
import org.programers.grids_and_circles.dto.orderItem.OrderItemResponseDto;
import org.programers.grids_and_circles.entity.Products;
import org.programers.grids_and_circles.repository.OrdersItemRepository;
import org.programers.grids_and_circles.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@Slf4j
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(value = "test")
class OrdersItemServiceTest {

    @Autowired
    private OrdersItemRepository ordersItemRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private OrdersItemService ordersItemService;

    private OrderItemRequestDto orderItemRequestDto;

    @BeforeAll
    public void setUp() {
        Products product = Products.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .productName("product")
                .price(1000L)
                .category("dfdf")
                .build();

        Products product1 = Products.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .productName("proddfduct")
                .price(1000L)
                .category("dfddfdf")
                .build();

        Products save = productsRepository.save(product);
        Products save1 = productsRepository.save(product1);
        orderItemRequestDto = new OrderItemRequestDto();

        orderItemRequestDto.setAddress("1234");
        orderItemRequestDto.setPostcode("1234");
        orderItemRequestDto.setEmail("1234");
        orderItemRequestDto.setProductQuantities(List.of(1234,1234));
        orderItemRequestDto.setProductsUUIDs(List.of(save.getProductId(),save1.getProductId()));

    }

    @AfterAll
    public void cleanUp() {
        ordersItemRepository.deleteAll();
    }
    @Test
    void createOrderItems() {
        //Given
        //WHen
        OrderItemResponseDto orderItems = ordersItemService.createOrderItems(orderItemRequestDto);

        //Then
        assertThat(orderItems.getOrderItems().isEmpty(), is(false));

    }
}