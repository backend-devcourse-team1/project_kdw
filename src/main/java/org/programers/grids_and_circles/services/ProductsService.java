package org.programers.grids_and_circles.services;

import org.programers.grids_and_circles.dto.products.ResponseProductDto;
import org.programers.grids_and_circles.entity.Products;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductsService {
    List<ResponseProductDto> readAllProduct();

    Optional<Products> readProductByUUID(UUID uuid);
}
