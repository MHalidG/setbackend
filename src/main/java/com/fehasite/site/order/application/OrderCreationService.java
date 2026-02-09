package com.fehasite.site.order.application;

import com.fehasite.site.order.domain.Order;
import com.fehasite.site.order.domain.OrderSet;
import com.fehasite.site.product.domain.Set;

public class OrderCreationService {

    public Order createFromSet(Set set) {

        // 1️⃣ Order oluştur
        Order order = Order.fromSet(set);


        return order;
    }
}
