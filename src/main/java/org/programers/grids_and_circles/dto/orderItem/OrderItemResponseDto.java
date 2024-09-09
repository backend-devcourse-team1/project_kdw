package org.programers.grids_and_circles.dto.orderItem;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class OrderItemResponseDto {
    @Builder.Default
    private List<Long> orderItems = new ArrayList<>();
}
