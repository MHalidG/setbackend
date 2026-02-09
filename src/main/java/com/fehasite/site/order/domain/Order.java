package com.fehasite.site.order.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    protected Order() {
        // JPA için
    }

    public Order(int totalPriceCents) {
        if (totalPriceCents <= 0) {
            throw new IllegalArgumentException("Total price must be greater than zero");
        }
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.totalPriceCents = totalPriceCents;
    }

    // --------------------
    // Davranışlar
    // --------------------

    public void addItem(OrderItem item) {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Cannot add item after order is paid");
        }
        item.attachTo(this);
        this.items.add(item);
    }

    public void markPaid() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Only CREATED orders can be paid");
        }
        this.status = OrderStatus.PAID;
    }

    public void startProduction() {
        if (status != OrderStatus.PAID) {
            throw new IllegalStateException("Order must be PAID before production");
        }
        this.status = OrderStatus.IN_PRODUCTION;
    }

    public void ship() {
        if (status != OrderStatus.IN_PRODUCTION) {
            throw new IllegalStateException("Order must be in production before shipping");
        }
        this.status = OrderStatus.SHIPPED;
    }

    public void complete() {
        if (status != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Order must be shipped before completion");
        }
        this.status = OrderStatus.COMPLETED;
    }

    public void cancel() {
        if (status == OrderStatus.IN_PRODUCTION || status == OrderStatus.SHIPPED) {
            throw new IllegalStateException("Order cannot be canceled after production started");
        }
        this.status = OrderStatus.CANCELED;
    }

    // --------------------
    // Getter'lar
    // --------------------

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getTotalPriceCents() {
        return totalPriceCents;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }
}
