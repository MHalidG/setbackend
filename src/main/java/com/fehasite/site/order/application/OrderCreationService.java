package com.fehasite.site.order.application;

import com.fehasite.site.order.domain.Order;
import com.fehasite.site.order.domain.OrderItem;
import com.fehasite.site.product.domain.Set;
import com.fehasite.site.product.domain.SetItem;

public class OrderCreationService {

    // Şimdilik repository yok
    // Akışı net görmek için sade tutuyoruz

    public Order createFromSet(Set set, CreateOrderFromSetCommand command) {

        // 1️⃣ Order oluştur
        Order order = new Order(command.finalPriceCents());

        // 2️⃣ Set içeriğini snapshot olarak kopyala
        for (SetItem setItem : set.getItems()) {

            OrderItem orderItem = new OrderItem(
                    setItem.getProduct().getName(),        // SNAPSHOT
                    command.finalPriceCents(),              // şimdilik basit
                    setItem.getQuantity()
            );

            order.addItem(orderItem);
        }

        // (ileride: orderRepository.save(order))

        return order;
    }
}
