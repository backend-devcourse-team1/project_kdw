package org.programers.grids_and_circles.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.programers.grids_and_circles.entity.superClass.DateSuperClass;

import java.time.LocalDateTime;


@Getter
@Table(name = "order_item")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//주문한 아이템 테이블
public class OrderItem extends DateSuperClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "category", length = 50, nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Products product;
    @Builder
    public OrderItem(LocalDateTime createdAt, LocalDateTime updatedAt, String category, Long price, int quantity) {
        super(createdAt, updatedAt);
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    //연관관계 편의 메소드

    public void setOrder(Orders order) {
        if (this.order != null) {
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        order.getOrderItems().add(this);
    }

    public void setProduct(Products products) {
        if (this.product != null) {
            this.product.getOrderItems().remove(this);
        }
        this.product = products;
        products.getOrderItems().add(this);
    }
}
