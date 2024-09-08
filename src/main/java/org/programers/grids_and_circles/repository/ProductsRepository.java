package org.programers.grids_and_circles.repository;

import org.programers.grids_and_circles.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductsRepository extends JpaRepository<Products, UUID> {

}
