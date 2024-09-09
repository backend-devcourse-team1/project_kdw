package org.programers.grids_and_circles.services.scheduling.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.programers.grids_and_circles.entity.Orders;
import org.programers.grids_and_circles.entity.enumClass.OrderStatus;
import org.programers.grids_and_circles.repository.OrdersRepository;
import org.programers.grids_and_circles.services.scheduling.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.programers.grids_and_circles.entity.enumClass.OrderStatus.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@ActiveProfiles("test")
class ScheduledTaskServiceImplTest {
    @Autowired
    private ScheduledTaskService scheduledTaskService;
    @Autowired
    private OrdersRepository ordersRepository;

    private Orders orders1;
    private Orders orders2;
    @BeforeEach
    public void setUp() {
        orders1 = Orders.builder()
                .email("fsdshsdgsd")
                .address("dfdsfaerw")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER_RECEIVED)
                .postcode("1dsfsd")
                .build();

        orders2 = Orders.builder()
                .address("안df양시")
                .email("dfdfd@gmail.com")
                .postcode("123df45")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderStatus(IN_PROGRESS)
                .build();
    }

    @Test
    @DisplayName("주문 상태 변환 테스트 주문완료 -> 배송중")
    @Order(1)
    void updateOrderStatusToInProgress() {
        //Given
        Orders save1 = ordersRepository.save(orders1);
        Orders save2 = ordersRepository.save(orders2);

        //When
        scheduledTaskService.updateOrderStatusToInProgress();
        Optional<Orders> findOrder1 = ordersRepository.findById(save1.getOrderId());
        Optional<Orders> findOrder2 = ordersRepository.findById(save2.getOrderId());

        //then
        assertThat(findOrder1.get().getOrderStatus(), is(IN_PROGRESS));
        assertThat(findOrder2.get().getOrderStatus(), is(IN_PROGRESS));
    }

    @Test
    @DisplayName("주문 상태 변환 테스트 배송중 -> 완료")
    @Order(2)
    void updateOrderStatusToDelivered() {
        //Given
        Orders save1 = ordersRepository.save(orders1);
        Orders save2 = ordersRepository.save(orders2);

        //When
        scheduledTaskService.updateOrderStatusToDelivered();
        Optional<Orders> findOrder1 = ordersRepository.findById(save1.getOrderId());
        Optional<Orders> findOrder2 = ordersRepository.findById(save2.getOrderId());

        //then
        assertThat(findOrder1.get().getOrderStatus(), is(ORDER_RECEIVED));
        assertThat(findOrder2.get().getOrderStatus(), is(DELIVERED));
    }

    @AfterEach
    public void cleanUp() {
        ordersRepository.deleteAll();
    }
}