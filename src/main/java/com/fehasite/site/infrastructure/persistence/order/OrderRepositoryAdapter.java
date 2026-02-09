package com.fehasite.site.infrastructure.persistence.order;

import com.fehasite.site.order.application.OrderRepository;
import com.fehasite.site.order.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository jpaRepository;

    public OrderRepositoryAdapter(JpaOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Order save(Order order) {
        return jpaRepository.save(order);
    }
}
