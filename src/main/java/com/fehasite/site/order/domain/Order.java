package com.fehasite.site.order.domain;

import com.fehasite.site.product.domain.Set;
import com.fehasite.site.product.domain.SetItem;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private int totalPriceCents;

    protected Order() {
        // JPA
    }

    private Order(int totalPriceCents) {
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.totalPriceCents = totalPriceCents;
    }

    /**
     * ✅ DOMAIN FACTORY
     * Sipariş SET üzerinden oluşturulur
     */
    public static Order fromSet(Set set) {

        int total = 0;

        for (SetItem item : set.getItems()) {
            total += set.getBasePriceCents() * item.getQuantity();
        }

        if (total <= 0) {
            throw new IllegalStateException("Order total price must be greater than zero");
        }

        return new Order(total);
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getTotalPriceCents() {
        return totalPriceCents;
    }
}
