package com.fehasite.site.order.application;

import com.fehasite.site.order.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
