package org.programers.grids_and_circles.services;

import org.programers.grids_and_circles.dto.orders.CreateOrderDto;
import org.programers.grids_and_circles.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersService {

    public Orders createOrder(CreateOrderDto createOrderDto);

    List<Orders> readAllOrder();

    void updateAllOrderStatus(List<Orders> ordersList);

    Optional<Orders> readByEmail(String email);
}
