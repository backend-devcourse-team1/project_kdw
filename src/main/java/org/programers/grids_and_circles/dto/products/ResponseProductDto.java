package org.programers.grids_and_circles.dto.products;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseProductDto {

    private UUID productId;
    private String productName;

    private String category;

    private Long price;
    private String description;
}
