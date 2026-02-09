package com.fehasite.site.infrastructure.persistence.product;

import com.fehasite.site.order.application.SetRepository;
import com.fehasite.site.product.domain.Set;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SetRepositoryAdapter implements SetRepository {

    private final JpaSetRepository jpaRepository;

    public SetRepositoryAdapter(JpaSetRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Set> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
