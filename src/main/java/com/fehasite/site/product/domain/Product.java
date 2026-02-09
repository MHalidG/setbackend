package com.fehasite.site.product.domain;

import com.fehasite.site.customization.domain.ProductionMethod;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductionMethod productionMethod;

    protected Product() {
        // JPA
    }

    public Product(String name, ProductionMethod productionMethod) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be blank");
        }
        this.name = name;
        this.productionMethod = productionMethod;
    }

    public String getName() {
        return name;
    }

    public ProductionMethod getProductionMethod() {
        return productionMethod;
    }
}
