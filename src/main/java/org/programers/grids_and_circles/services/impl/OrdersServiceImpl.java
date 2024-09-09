package org.programers.grids_and_circles.services.impl;

import org.programers.grids_and_circles.dto.mapper.OrderMapper;
import org.programers.grids_and_circles.dto.orders.CreateOrderDto;
import org.programers.grids_and_circles.entity.Orders;
import org.programers.grids_and_circles.repository.OrdersRepository;
import org.programers.grids_and_circles.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository repository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrdersServiceImpl(OrdersRepository repository, OrderMapper orderMapper) {
        this.repository = repository;
        this.orderMapper = orderMapper;
    }


    @Override
    public Orders createOrder(CreateOrderDto createOrderDto) {
        Orders orders = orderMapper.createOrderDtoToOrderEntity(createOrderDto);

        return repository.save(orders);
    }

    @Override
    public List<Orders> readAllOrder() {
        return repository.findAll();
    }

    @Override
    public void updateAllOrderStatus(List<Orders> ordersList) {
        repository.saveAll(ordersList);
    }

    @Override
    public Optional<Orders> readByEmail(String email) {
        return repository.findByEmail(email);
    }
}
