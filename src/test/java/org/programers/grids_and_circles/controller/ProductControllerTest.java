package org.programers.grids_and_circles.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.programers.grids_and_circles.dto.products.ResponseProductDto;
import org.programers.grids_and_circles.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

    private ResponseProductDto responseProductDto1;
    private ResponseProductDto responseProductDto2;

    @BeforeAll
    public void setUp() {
        responseProductDto1 = ResponseProductDto.builder()
                .productId(UUID.randomUUID())
                .productName("sdfhgw")
                .price(2134L)
                .category("sdfafas")
                .description("dsfsfsdf")
                .build();
        responseProductDto2 = ResponseProductDto.builder()
                .productId(UUID.randomUUID())
                .productName("sdffdfhgw")
                .price(213344L)
                .category("sdfafadfs")
                .description("dsfsgfsdf")
                .build();
    }

    @Test
    @DisplayName("getAllProduct 호출 시 모든 상품 반환")
    void getAllProduct() throws Exception {
        // Given
        List<ResponseProductDto> mockProductList = Arrays.asList(responseProductDto1, responseProductDto2);
        when(productsService.readAllProduct()).thenReturn(mockProductList);

        // When & Then
        mockMvc.perform(get("/avi/v1/product/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].productId").value(responseProductDto1.getProductId().toString()))
                .andExpect(jsonPath("$[0].productName").value(responseProductDto1.getProductName()))
                .andExpect(jsonPath("$[0].price").value(responseProductDto1.getPrice()))
                .andExpect(jsonPath("$[0].category").value(responseProductDto1.getCategory()))
                .andExpect(jsonPath("$[0].description").value(responseProductDto1.getDescription()))
                .andExpect(jsonPath("$[1].productId").value(responseProductDto2.getProductId().toString()))
                .andExpect(jsonPath("$[1].productName").value(responseProductDto2.getProductName()))
                .andExpect(jsonPath("$[1].price").value(responseProductDto2.getPrice()))
                .andExpect(jsonPath("$[1].category").value(responseProductDto2.getCategory()))
                .andExpect(jsonPath("$[1].description").value(responseProductDto2.getDescription()));
    }
}