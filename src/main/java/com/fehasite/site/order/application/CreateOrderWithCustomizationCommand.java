package com.fehasite.site.order.application;

import com.fehasite.site.customization.domain.CustomizationType;
import com.fehasite.site.customization.domain.ProductionMethod;

public record CreateOrderWithCustomizationCommand(
        Long setId,
        int finalPriceCents,
        CustomizationType customizationType,
        ProductionMethod productionMethod,
        String textValue,
        String imageKey
) {
}
