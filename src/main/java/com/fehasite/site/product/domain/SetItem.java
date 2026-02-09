package com.fehasite.site.product.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "set_items")
public class SetItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Set set;

    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    protected SetItem() {
        // JPA
    }

    SetItem(Set set, Product product, int quantity) {
        this.set = set;
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
