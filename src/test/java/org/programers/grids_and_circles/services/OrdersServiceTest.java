package org.programers.grids_and_circles.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.programers.grids_and_circles.dto.orders.CreateOrderDto;
import org.programers.grids_and_circles.entity.Orders;
import org.programers.grids_and_circles.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@Slf4j
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class OrdersServiceTest {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersRepository repository;
    @BeforeAll
    public void setUP() {

    }

    @AfterAll
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("orderService Order 생성 테스트")
    void createOrder() {
        //Given
        CreateOrderDto createOrderDto = CreateOrderDto.builder()
                .address("124")
                .email("1234sdsdssd")
                .postcode("1234")
                .build();
        //When
        Orders order = ordersService.createOrder(createOrderDto);

        //Then
        assertThat(order.getAddress(), is(createOrderDto.getAddress()));
        assertThat(order.getEmail(), is(createOrderDto.getEmail()));
        assertThat(order.getPostcode(), is(createOrderDto.getPostcode()));
    }
}