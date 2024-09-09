package org.programers.grids_and_circles.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.programers.grids_and_circles.entity.enumClass.OrderStatus.DELIVERED;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class EntityTest {
    @Autowired
    private EntityManagerFactory emf;

    @Test
    @DisplayName("entity 테스트")
    public void orderItemEntityTest() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Given
        Products products = Products.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .productName("product")
                .price(1000L)
                .category("dfdf")
                .build();

        Orders orders = Orders.builder()
                .address("안양시")
                .email("kimdw990823@gmail.com")
                .postcode("12345")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderStatus(DELIVERED)
                .build();

        OrderItem orderItem = OrderItem.builder()
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .price(1000L)
                .category("1234")
                .quantity(100)
                .build();

        orderItem.setProduct(products);
        orderItem.setOrder(orders);

        // When
        entityManager.persist(orders);
        entityManager.persist(products);
        entityManager.persist(orderItem);
        transaction.commit();

        // Then
        Products selectedProducts = entityManager.createQuery(
                "SELECT p FROM Products p",
                Products.class
        ).getSingleResult();

        Orders selectedOrder = entityManager.createQuery(
                "SELECT o FROM Orders o",
                Orders.class
        ).getSingleResult();

        OrderItem selectedOrderItem = entityManager.createQuery(
                "SELECT orderItem FROM OrderItem orderItem",
                OrderItem.class
        ).getSingleResult();

        assertThat(selectedProducts, samePropertyValuesAs(products));
        assertThat(selectedOrder, samePropertyValuesAs(orders));
        assertThat(selectedOrderItem, samePropertyValuesAs(orderItem));

    }
}
