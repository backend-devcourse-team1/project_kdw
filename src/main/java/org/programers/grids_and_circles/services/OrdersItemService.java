package org.programers.grids_and_circles.services;

import org.programers.grids_and_circles.dto.orderItem.OrderItemRequestDto;
import org.programers.grids_and_circles.dto.orderItem.OrderItemResponseDto;

public interface OrdersItemService {
    OrderItemResponseDto createOrderItems(OrderItemRequestDto orderItemRequestDto);
}
