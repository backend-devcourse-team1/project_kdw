package org.programers.grids_and_circles.controller;

import org.programers.grids_and_circles.dto.products.ResponseProductDto;
import org.programers.grids_and_circles.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("avi/v1/product")
public class ProductController {

    private final ProductsService productsService;
    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseProductDto>> getAllProduct() {
        List<ResponseProductDto> responseProductDtos = productsService.readAllProduct();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseProductDtos);
    }


}
