package com.fehasite.site.infrastructure.persistence.customization;

import com.fehasite.site.customization.domain.Customization;
import com.fehasite.site.order.application.CustomizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomizationRepositoryAdapter implements CustomizationRepository {

    private final JpaCustomizationRepository jpaRepository;

    public CustomizationRepositoryAdapter(JpaCustomizationRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Customization save(Customization customization) {
        return jpaRepository.save(customization);
    }
}
