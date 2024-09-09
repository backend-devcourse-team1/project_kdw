package org.programers.grids_and_circles.dto.mapper;

import org.programers.grids_and_circles.dto.orders.CreateOrderDto;
import org.programers.grids_and_circles.entity.Orders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.programers.grids_and_circles.entity.enumClass.OrderStatus.ORDER_RECEIVED;

@Component
public class OrderMapper {
    public Orders createOrderDtoToOrderEntity(CreateOrderDto createOrderDto) {
        return Orders.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderStatus(ORDER_RECEIVED)
                .email(createOrderDto.getEmail())
                .address(createOrderDto.getAddress())
                .postcode(createOrderDto.getPostcode())
                .build();
    }
}
