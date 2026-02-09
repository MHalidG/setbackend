package com.fehasite.site.order.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @Column(nullable = false)
    private String productName;     // snapshot

    @Column(nullable = false)
    private int unitPriceCents;     // snapshot

    @Column(nullable = false)
    private int quantity;

    protected OrderItem() {
        // JPA
    }

    public OrderItem(String productName, int unitPriceCents, int quantity) {
        if (unitPriceCents <= 0 || quantity <= 0) {
            throw new IllegalArgumentException("Price and quantity must be positive");
        }
        this.productName = productName;
        this.unitPriceCents = unitPriceCents;
        this.quantity = quantity;
    }

    void attachTo(Order order) {
        this.order = order;
    }

    // Getter'lar

    public String getProductName() {
        return productName;
    }

    public int getUnitPriceCents() {
        return unitPriceCents;
    }

    public int getQuantity() {
        return quantity;
    }
}
