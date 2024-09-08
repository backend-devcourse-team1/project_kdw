package org.programers.grids_and_circles.dto.orderItem;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class OrderItemRequestDto {
    private String email;
    private String address;
    private String postcode;

    private List<UUID> productsUUIDs;
    private List<Integer> productQuantities;
}
