package org.programers.grids_and_circles.dto.orders;

import lombok.Builder;
import lombok.Data;
import org.programers.grids_and_circles.entity.Orders;

import java.time.LocalDateTime;

import static org.programers.grids_and_circles.entity.enumClass.OrderStatus.ORDER_RECEIVED;

@Data
@Builder
public class CreateOrderDto {
    private String email;
    private String address;
    private String postcode;

    public Orders dtoToEntity() {
        return Orders.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderStatus(ORDER_RECEIVED)
                .email(this.email)
                .address(this.address)
                .postcode(this.postcode)
                .build();
    }
}
