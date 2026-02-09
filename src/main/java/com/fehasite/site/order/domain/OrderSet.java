package com.fehasite.site.order.domain;

import com.fehasite.site.product.domain.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "order_sets")
public class OrderSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String setName;
    private int priceCents;

    @ManyToOne(optional = false)
    private Order order;

    protected OrderSet() {}

    private OrderSet(String setName, int priceCents) {
        this.setName = setName;
        this.priceCents = priceCents;
    }

    public static OrderSet from(Set set) {
        return new OrderSet(
                set.getName(),
                set.getBasePriceCents()
        );
    }

    void attachTo(Order order) {
        this.order = order;
    }

    public int totalPrice() {
        return priceCents;
    }
}