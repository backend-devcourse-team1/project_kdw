package org.programers.grids_and_circles.dto.mapper;

import org.programers.grids_and_circles.dto.products.ResponseProductDto;
import org.programers.grids_and_circles.entity.Products;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ResponseProductDto ProductEntityToResponseProductDto(Products products) {

        return ResponseProductDto.builder()
                .productName(products.getProductName())
                .price(products.getPrice())
                .productId(products.getProductId())
                .description(products.getDescription())
                .category(products.getCategory())
                .build();
    }

    public List<ResponseProductDto> ProductEntityListToResponseProductDtoList(List<Products> productsList) {
        return productsList.stream()
                .map(this::ProductEntityToResponseProductDto)
                .collect(Collectors.toList());
    }
}
