package com.fehasite.site.order.application;

import com.fehasite.site.order.domain.Order;
import com.fehasite.site.customization.domain.Customization;
import com.fehasite.site.customization.domain.CustomizationType;
import com.fehasite.site.customization.domain.ProductionMethod;

public class OrderService {

    // Şimdilik repository yok
    // Akışı görmek için saf hali

    public void createOrder(CreateOrderCommand command) {

        // 1️⃣ Order oluştur
        Order order = new Order(command.totalPriceCents());

        // (ileride: orderRepository.save(order))
        Long orderId = 1L; // simülasyon

        // 2️⃣ Customization oluştur
        Customization customization;

        if (command.customizationType() == CustomizationType.TEXT) {
            customization = Customization.textCustomization(
                    orderId,
                    ProductionMethod.LASER,
                    command.textValue()
            );
        } else {
            customization = Customization.imageCustomization(
                    orderId,
                    ProductionMethod.SUBLIMATION,
                    command.imageKey()
            );
        }

        // (ileride: customizationRepository.save(customization))
    }
}
