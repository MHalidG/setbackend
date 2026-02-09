package com.fehasite.site.customization.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customizations")
public class Customization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId; // gevşek bağ

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductionMethod productionMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomizationType type;

    // TEXT için
    @Column(length = 500)
    private String textValue;

    // IMAGE için (dosya yolu / object storage key)
    private String imageKey;

    protected Customization() {
        // JPA
    }

    private Customization(Long orderId,
                          ProductionMethod productionMethod,
                          CustomizationType type,
                          String textValue,
                          String imageKey) {
        this.orderId = orderId;
        this.productionMethod = productionMethod;
        this.type = type;
        this.textValue = textValue;
        this.imageKey = imageKey;
    }

    // -------- Factory'ler (davranış odaklı) --------

    public static Customization textCustomization(Long orderId,
                                                  ProductionMethod method,
                                                  String text) {
        if (method != ProductionMethod.LASER) {
            throw new IllegalArgumentException("Text customization requires LASER method");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be blank");
        }
        return new Customization(orderId, method, CustomizationType.TEXT, text, null);
    }

    public static Customization imageCustomization(Long orderId,
                                                   ProductionMethod method,
                                                   String imageKey) {
        if (method != ProductionMethod.SUBLIMATION) {
            throw new IllegalArgumentException("Image customization requires SUBLIMATION method");
        }
        if (imageKey == null || imageKey.isBlank()) {
            throw new IllegalArgumentException("Image key cannot be blank");
        }
        return new Customization(orderId, method, CustomizationType.IMAGE, null, imageKey);
    }

    // -------- Getter'lar --------

    public Long getOrderId() {
        return orderId;
    }

    public ProductionMethod getProductionMethod() {
        return productionMethod;
    }

    public CustomizationType getType() {
        return type;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getImageKey() {
        return imageKey;
    }
}
