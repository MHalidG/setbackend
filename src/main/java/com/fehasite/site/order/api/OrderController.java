package com.fehasite.site.order.api;

import com.fehasite.site.order.application.CreateOrderWithCustomizationCommand;
import com.fehasite.site.order.application.OrderOrchestrationService;
import com.fehasite.site.order.domain.Order;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderOrchestrationService orchestrationService;

    public OrderController(OrderOrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> create(@Valid @RequestBody CreateOrderRequest request) {

        CreateOrderWithCustomizationCommand command =
                new CreateOrderWithCustomizationCommand(
                        request.setId(),
                        request.finalPriceCents(),
                        request.customizationType(),
                        request.productionMethod(),
                        request.textValue(),
                        request.imageKey()
                );

        Order order = orchestrationService.createOrder(command);

        return ResponseEntity.ok(
                new CreateOrderResponse(order.getId(), order.getStatus().name())
        );
    }
}
