package org.programers.grids_and_circles.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.programers.grids_and_circles.entity.superClass.DateSuperClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//팔고있는 상품 관리 테이블
public class Products extends DateSuperClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_name", length = 20, nullable = false)
    private String productName;

    @Column(name = "category", length = 50, nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "description", length = 500, nullable = true) // null 허용해주면 기본 값으로 null이 들어감
    private String description;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Products(LocalDateTime createdAt, LocalDateTime updatedAt, String productName, String category, Long price, String description ) {
        super(createdAt, updatedAt);
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;

    }
}
