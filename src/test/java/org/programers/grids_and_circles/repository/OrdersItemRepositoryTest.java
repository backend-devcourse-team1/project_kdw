package org.programers.grids_and_circles.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.programers.grids_and_circles.entity.OrderItem;
import org.programers.grids_and_circles.entity.Orders;
import org.programers.grids_and_circles.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.programers.grids_and_circles.entity.enumClass.OrderStatus.DELIVERED;

@SpringBootTest
@Slf4j
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class OrdersItemRepositoryTest {

    @Autowired
    private OrdersItemRepository ordersItemRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductsRepository productsRepository;
    private Products product;
    private Orders order;
    @BeforeAll()
    public void setUp() {
        product = Products.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .productName("product")
                .price(1000L)
                .category("dfdf")
                .build();

        order = Orders.builder()
                .address("안양시")
                .email("kimdw990823@gmail.com")
                .postcode("12345")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderStatus(DELIVERED)
                .build();
        ordersRepository.save(order);
        productsRepository.save(product);
    }

    @Test
    @DisplayName("orderItems 생성 테스트")
    public void OrderItemsCreateTest() {
        //Given
        OrderItem orderItem = OrderItem.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .price(1000L)
                .category("1234")
                .quantity(100)
                .build();
        orderItem.setOrder(order);
        orderItem.setProduct(product);

        //When
        OrderItem save = ordersItemRepository.save(orderItem);

        //
        assertThat(save, samePropertyValuesAs(orderItem));

    }

    @AfterAll()
    public void cleanUp() {
        productsRepository.deleteAll();
        ordersItemRepository.deleteAll();
    }
}