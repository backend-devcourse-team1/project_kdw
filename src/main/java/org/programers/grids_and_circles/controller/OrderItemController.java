package org.programers.grids_and_circles.controller;

import jakarta.persistence.EntityNotFoundException;
import org.programers.grids_and_circles.dto.orderItem.OrderItemRequestDto;
import org.programers.grids_and_circles.dto.orderItem.OrderItemResponseDto;
import org.programers.grids_and_circles.services.OrdersItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orderItem")
public class OrderItemController {

    private final OrdersItemService ordersItemService;

    @Autowired
    public OrderItemController(OrdersItemService ordersItemService) {
        this.ordersItemService = ordersItemService;
    }

    @PostMapping

    public ResponseEntity<OrderItemResponseDto> createOrderItem(@RequestBody OrderItemRequestDto orderItemRequestDto) {
        try {
            OrderItemResponseDto orderItems = ordersItemService.createOrderItems(orderItemRequestDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(orderItems);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);


        }
    }
}
