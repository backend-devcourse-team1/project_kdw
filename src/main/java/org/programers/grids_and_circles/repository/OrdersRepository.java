package org.programers.grids_and_circles.repository;

import org.programers.grids_and_circles.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
    Orders findByEmail(String email);
}
