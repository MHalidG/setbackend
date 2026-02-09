package com.fehasite.site.order.api;

public record CreateOrderResponse(
        Long orderId,
        String status
) {
}
