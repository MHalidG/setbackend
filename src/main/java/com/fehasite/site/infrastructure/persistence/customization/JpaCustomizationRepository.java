package com.fehasite.site.infrastructure.persistence.customization;

import com.fehasite.site.customization.domain.Customization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomizationRepository extends JpaRepository<Customization, Long> {
}
