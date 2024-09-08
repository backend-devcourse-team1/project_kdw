package org.programers.grids_and_circles.repository;

import org.programers.grids_and_circles.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersItemRepository extends JpaRepository<OrderItem, UUID> {

}
