package com.fehasite.site.customization.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customizations")
public class Customization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomizationType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductionMethod productionMethod;

    @Column
    private String textValue;

    @Column
    private String imageKey;

    protected Customization() {
        // JPA
    }

    private Customization(
            CustomizationType type,
            ProductionMethod productionMethod,
            String textValue,
            String imageKey
    ) {
        this.type = type;
        this.productionMethod = productionMethod;
        this.textValue = textValue;
        this.imageKey = imageKey;
    }

    public static Customization text(String text, ProductionMethod method) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be blank");
        }

        return new Customization(
                CustomizationType.TEXT,
                method,
                text,
                null
        );
    }

    public static Customization image(String imageKey, ProductionMethod method) {
        if (imageKey == null || imageKey.isBlank()) {
            throw new IllegalArgumentException("Image key cannot be blank");
        }

        return new Customization(
                CustomizationType.IMAGE,
                method,
                null,
                imageKey
        );
    }
}
