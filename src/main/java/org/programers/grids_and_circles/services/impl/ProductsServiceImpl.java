package org.programers.grids_and_circles.services.impl;

import org.programers.grids_and_circles.dto.mapper.ProductMapper;
import org.programers.grids_and_circles.dto.products.ResponseProductDto;
import org.programers.grids_and_circles.entity.Products;
import org.programers.grids_and_circles.repository.ProductsRepository;
import org.programers.grids_and_circles.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository repository;
    private final ProductMapper mapper;
    @Autowired
    public ProductsServiceImpl(ProductsRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseProductDto> readAllProduct() {
        List<Products> all = repository.findAll();
        return mapper.ProductEntityListToResponseProductDtoList(all);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Products> readProductByUUID(UUID uuid) {
        return repository.findById(uuid);
    }
}
