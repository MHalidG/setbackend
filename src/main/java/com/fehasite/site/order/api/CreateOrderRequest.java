package com.fehasite.site.order.api;

import com.fehasite.site.customization.domain.CustomizationType;
import com.fehasite.site.customization.domain.ProductionMethod;
import jakarta.validation.constraints.*;

public record CreateOrderRequest(

        @NotNull(message = "setId is required")
        Long setId,

        @Positive(message = "finalPriceCents must be positive")
        int finalPriceCents,

        @NotNull(message = "customizationType is required")
        CustomizationType customizationType,

        @NotNull(message = "productionMethod is required")
        ProductionMethod productionMethod,

        @Size(max = 500, message = "textValue max length is 500")
        String textValue,

        String imageKey
) {
}
