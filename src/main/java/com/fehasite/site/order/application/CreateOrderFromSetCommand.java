package com.fehasite.site.order.application;

public record CreateOrderFromSetCommand(
        Long setId,
        int finalPriceCents
) {
}
