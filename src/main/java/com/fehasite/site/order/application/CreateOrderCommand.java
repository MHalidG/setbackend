package com.fehasite.site.order.application;

import com.fehasite.site.customization.domain.ProductionMethod;
import com.fehasite.site.customization.domain.CustomizationType;

public record CreateOrderCommand(
        int totalPriceCents,
        ProductionMethod productionMethod,
        CustomizationType customizationType,
        String textValue,
        String imageKey
) {
}
