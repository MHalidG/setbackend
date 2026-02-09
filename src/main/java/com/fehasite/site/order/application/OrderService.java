package com.fehasite.site.order.application;

import com.fehasite.site.order.domain.Order;
import com.fehasite.site.product.domain.Set;
import com.fehasite.site.product.domain.SetItem;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final SetRepository setRepository;

    public OrderService(
            OrderRepository orderRepository,
            SetRepository setRepository
    ) {
        this.orderRepository = orderRepository;
        this.setRepository = setRepository;
    }
}
/*
    public Order createOrderWithSets(Long[] setIds) {

        if (setIds == null || setIds.length == 0) {
            throw new IllegalArgumentException("Order must contain at least one set");
        }

        Order order = Order.create();

        for (Long setId : setIds) {
            Set set = setRepository.findById(setId)
                    .orElseThrow(() -> new IllegalArgumentException("Set not found"));

            for (SetItem setItem : set.getItems()) {
                OrderItem orderItem = new OrderItem(
                        setItem.getProduct().getName(),
                        set.getBasePriceCents(),
                        setItem.getQuantity()
                );
                order.addItem(orderItem);
            }
        }

        return orderRepository.save(order);
    } */




