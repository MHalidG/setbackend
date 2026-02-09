package com.fehasite.site.infrastructure.persistence.order;

import com.fehasite.site.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
