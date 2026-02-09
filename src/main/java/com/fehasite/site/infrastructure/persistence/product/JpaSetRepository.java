package com.fehasite.site.infrastructure.persistence.product;

import com.fehasite.site.product.domain.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSetRepository extends JpaRepository<Set, Long> {
}
