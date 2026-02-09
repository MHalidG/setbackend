package com.fehasite.site.order.domain;

public enum OrderStatus {

    CREATED,        // sipariş oluşturuldu
    PAID,           // ödeme alındı
    IN_PRODUCTION,  // üretime alındı
    SHIPPED,        // kargoya verildi
    COMPLETED,      // teslim edildi
    CANCELED        // üretim başlamadan iptal
}
