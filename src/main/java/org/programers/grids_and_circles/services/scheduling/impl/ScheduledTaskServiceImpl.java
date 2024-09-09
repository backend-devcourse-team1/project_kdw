package org.programers.grids_and_circles.services.scheduling.impl;

import org.programers.grids_and_circles.entity.Orders;
import org.programers.grids_and_circles.entity.enumClass.OrderStatus;
import org.programers.grids_and_circles.services.OrdersService;
import org.programers.grids_and_circles.services.scheduling.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduledTaskServiceImpl implements ScheduledTaskService {
    private final OrdersService ordersService;

    @Autowired
    public ScheduledTaskServiceImpl(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    @Scheduled(cron = "0 0 14 * * ?")// 매 2시에 작동
    public void updateOrderStatusToInProgress() {
        List<Orders> orders = ordersService.readAllOrder();

        List<Orders> updatedOrders = orders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.ORDER_RECEIVED) // ORDER_RECEIVED 상태 필터링
                .peek(order -> {
                    order.modifyOrderStatus(OrderStatus.IN_PROGRESS);
                    order.updateUpdatedAt();
                }) // orderStatus 값 변경
                .collect(Collectors.toList()); // 변경된 주문들을 리스트로 수집

        ordersService.updateAllOrderStatus(updatedOrders);
    }
    @Override
    @Scheduled(cron = "0 0 13 * * ?")// 매 2시에 작동
    public void updateOrderStatusToDelivered() {
        List<Orders> orders = ordersService.readAllOrder();

        List<Orders> updatedOrders = orders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.IN_PROGRESS) // ORDER_RECEIVED 상태 필터링
                .peek(order -> {
                    order.modifyOrderStatus(OrderStatus.DELIVERED);
                    order.updateUpdatedAt();
                }) // orderStatus 값 변경
                .collect(Collectors.toList()); // 변경된 주문들을 리스트로 수집

        ordersService.updateAllOrderStatus(updatedOrders);
    }
}
