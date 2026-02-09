package com.fehasite.site.product.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sets")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int basePriceCents;

    @OneToMany(
            mappedBy = "set",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SetItem> items = new ArrayList<>();

    protected Set() {
        // JPA
    }

    public Set(String name, int basePriceCents) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Set name cannot be blank");
        }
        if (basePriceCents <= 0) {
            throw new IllegalArgumentException("Base price must be positive");
        }
        this.name = name;
        this.basePriceCents = basePriceCents;
    }

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.items.add(new SetItem(this, product, quantity));
    }

    public String getName() {
        return name;
    }

    public int getBasePriceCents() {
        return basePriceCents;
    }

    public List<SetItem> getItems() {
        return List.copyOf(items);
    }
}
