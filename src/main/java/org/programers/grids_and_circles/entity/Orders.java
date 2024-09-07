package org.programers.grids_and_circles.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.programers.grids_and_circles.entity.enumClass.OrderStatus;
import org.programers.grids_and_circles.entity.superClass.DateSuperClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//주문 정보 테이
public class Orders extends DateSuperClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID orderId; // GenerationType.UUID 옵션 주면  BINARY(16)로 매핑

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "postcode", length = 200, nullable = false)
    private String postcode;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    @Builder

    public Orders(LocalDateTime createdAt, LocalDateTime updatedAt, String email, String address, String postcode, OrderStatus orderStatus) {
        super(createdAt, updatedAt);
        this.email = email;
        this.orderStatus = orderStatus;
        this.address = address;
        this.postcode = postcode;
    }
}
