package org.programers.grids_and_circles.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.programers.grids_and_circles.entity.Orders;
import org.programers.grids_and_circles.entity.enumClass.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.programers.grids_and_circles.entity.enumClass.OrderStatus.DELIVERED;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class OrderRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    private Orders order;

    @BeforeAll
    public void setUp() {
        order = Orders.builder()
                .address("안양시")
                .email("kimdw990823@gmail.com")
                .postcode("12345")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderStatus(DELIVERED)
                .build();
    }

    @Test
    @DisplayName(value = "order 생성 테스트")
    @Order(1)
    public void orderCreateTest() {
        //When
        Orders save = ordersRepository.save(order);

        //Then
        assertThat(save, samePropertyValuesAs(order));
    }

    @Test
    @DisplayName(value = "order_status 수정 테스트")
    @Order(2)
    public void orderUpdateTest() {
        //Given
        Orders byEmail = ordersRepository.findByEmail(order.getEmail());
        byEmail.modifyOrderStatus(OrderStatus.CANCELLED);

        //When
        Orders save = ordersRepository.save(byEmail);

        //Then
        assertThat(byEmail.getOrderStatus(), is(byEmail.getOrderStatus()));
    }

    @AfterAll()
    public void cleanUp() {
        ordersRepository.deleteAll();
    }
}