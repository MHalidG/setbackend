package com.fehasite.site.order.application;

import com.fehasite.site.accounting.application.AccountingRepository;
import com.fehasite.site.accounting.domain.AccountingEntry;
import com.fehasite.site.customization.domain.Customization;
import com.fehasite.site.customization.domain.CustomizationType;
import com.fehasite.site.order.domain.Order;
import com.fehasite.site.product.domain.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderOrchestrationService {

    private final OrderRepository orderRepository;
    private final CustomizationRepository customizationRepository;
    private final SetRepository setRepository;
    private final AccountingRepository accountingRepository;

    public OrderOrchestrationService(
            OrderRepository orderRepository,
            CustomizationRepository customizationRepository,
            SetRepository setRepository,
            AccountingRepository accountingRepository
    ) {
        this.orderRepository = orderRepository;
        this.customizationRepository = customizationRepository;
        this.setRepository = setRepository;
        this.accountingRepository = accountingRepository;
    }

    @Transactional
    public Order createOrder(CreateOrderWithCustomizationCommand command) {

        validateCustomization(command);

        // 1️⃣ Set’i yükle
        Set set = setRepository.findById(command.setId())
                .orElseThrow(() -> new IllegalArgumentException("Set not found"));

        // 2️⃣ Order DOMAIN'DE oluşturulur
        Order order = Order.fromSet(set);

        // 3️⃣ Order kaydet (ID oluşur)
        Order savedOrder = orderRepository.save(order);

        // 4️⃣ Customization oluştur
        Customization customization =
                command.customizationType() == CustomizationType.TEXT
                        ? Customization.text(
                        command.textValue(),
                        command.productionMethod()
                )
                        : Customization.image(
                        command.imageKey(),
                        command.productionMethod()
                );

        customizationRepository.save(customization);

        // 5️⃣ Accounting kaydı
        accountingRepository.save(
                AccountingEntry.sale(
                        savedOrder.getId(),
                        savedOrder.getTotalPriceCents()
                )
        );

        return savedOrder;
    }

    private void validateCustomization(CreateOrderWithCustomizationCommand command) {

        if (command.customizationType() == CustomizationType.TEXT &&
                (command.textValue() == null || command.textValue().isBlank())) {
            throw new IllegalArgumentException("textValue is required for TEXT customization");
        }

        if (command.customizationType() == CustomizationType.IMAGE &&
                (command.imageKey() == null || command.imageKey().isBlank())) {
            throw new IllegalArgumentException("imageKey is required for IMAGE customization");
        }
    }
}
