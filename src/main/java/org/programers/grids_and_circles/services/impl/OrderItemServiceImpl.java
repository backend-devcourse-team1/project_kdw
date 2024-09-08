package org.programers.grids_and_circles.services.impl;

import org.programers.grids_and_circles.dto.orderItem.OrderItemRequestDto;
import org.programers.grids_and_circles.dto.orderItem.OrderItemResponseDto;
import org.programers.grids_and_circles.dto.orders.CreateOrderDto;
import org.programers.grids_and_circles.entity.OrderItem;
import org.programers.grids_and_circles.entity.Orders;
import org.programers.grids_and_circles.repository.OrdersItemRepository;
import org.programers.grids_and_circles.services.OrdersItemService;
import org.programers.grids_and_circles.services.OrdersService;
import org.programers.grids_and_circles.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderItemServiceImpl implements OrdersItemService {
    private final OrdersItemRepository ordersItemRepository;
    private final OrdersService ordersService;
    private final ProductsService productsService;

    @Autowired
    public OrderItemServiceImpl(OrdersItemRepository ordersItemRepository, OrdersService ordersService, ProductsService productsService) {
        this.ordersItemRepository = ordersItemRepository;
        this.ordersService = ordersService;
        this.productsService = productsService;
    }

    @Override
    @Transactional
    public OrderItemResponseDto createOrderItems(OrderItemRequestDto orderItemRequestDto) {
        CreateOrderDto createOrderDto = CreateOrderDto.builder()
                .email(orderItemRequestDto.getEmail())
                .postcode(orderItemRequestDto.getPostcode())
                .address(orderItemRequestDto.getAddress())
                .build();

        Orders order = ordersService.createOrder(createOrderDto);
        OrderItemResponseDto orderItemResponseDto = OrderItemResponseDto.builder().build();
        for (int i = 0; i < orderItemRequestDto.getProductsUUIDs().size(); i++) {
            UUID productUUID = orderItemRequestDto.getProductsUUIDs().get(i);
            Integer quantity = orderItemRequestDto.getProductQuantities().get(i);

            productsService.readProductByUUID(orderItemRequestDto.getProductsUUIDs().get(i))
                    .ifPresentOrElse(
                            product -> {
                                OrderItem orderItem = OrderItem.builder()
                                        .createdAt(LocalDateTime.now())
                                        .updatedAt(LocalDateTime.now())
                                        .price(product.getPrice())
                                        .quantity(quantity)
                                        .category(product.getCategory())
                                        .build();

                                orderItem.setOrder(order);
                                orderItem.setProduct(product);

                                OrderItem save = ordersItemRepository.save(orderItem);
                                orderItemResponseDto.getOrderItems().add(save.getSeq());
                            },
                            () -> {
                                throw new RuntimeException("Product not found for UUID: " + productUUID);
                            }
                    );
        }
        return orderItemResponseDto;
    }
}
