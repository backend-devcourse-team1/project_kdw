package org.programers.grids_and_circles.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.programers.grids_and_circles.dto.orderItem.OrderItemRequestDto;
import org.programers.grids_and_circles.dto.orderItem.OrderItemResponseDto;
import org.programers.grids_and_circles.services.OrdersItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderItemController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdersItemService ordersItemService;

    private OrderItemResponseDto orderItemResponseDto;

    private final UUID productId1 = UUID.randomUUID();  // 실제 UUID 생성
    private final UUID productId2 = UUID.randomUUID();  // 실제 UUID 생성

    @BeforeAll
    public void setUp() {


        orderItemResponseDto = OrderItemResponseDto.builder()
                .orderItems(Arrays.asList(1L, 2L))
                .build();
    }

    @Test
    @DisplayName("createOrderItem 호출 시 주문 항목 생성")
    void createOrderItem() throws Exception {
        // Given
        when(ordersItemService.createOrderItems(any(OrderItemRequestDto.class)))
                .thenReturn(orderItemResponseDto);

        // When & Then
        mockMvc.perform(post("/api/v1/orderItem/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"email\": \"test@example.com\", \"address\": \"123 Test St\", \"postcode\": \"12345\", \"productsUUIDs\": [\"" + productId1.toString() + "\", \"" + productId2.toString() + "\"], \"productQuantities\": [2, 3] }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Content-Type 검사
                .andExpect(jsonPath("$.orderItems[0]").value(1L))
                .andExpect(jsonPath("$.orderItems[1]").value(2L));
    }
}