package com.fehasite.site.order.application;

import com.fehasite.site.customization.domain.Customization;

public interface CustomizationRepository {
    Customization save(Customization customization);
}
