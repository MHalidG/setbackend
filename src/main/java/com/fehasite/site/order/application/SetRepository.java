package com.fehasite.site.order.application;

import com.fehasite.site.product.domain.Set;

import java.util.Optional;

public interface SetRepository {
    Optional<Set> findById(Long id);
}
