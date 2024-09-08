package org.programers.grids_and_circles.services;

import org.programers.grids_and_circles.dto.orders.CreateOrderDto;
import org.programers.grids_and_circles.entity.Orders;

public interface OrdersService {

    public Orders createOrder(CreateOrderDto createOrderDto);
}
