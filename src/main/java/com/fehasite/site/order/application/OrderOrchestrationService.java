package com.fehasite.site.order.application;
import com.fehasite.site.accounting.application.AccountingRepository;
import com.fehasite.site.accounting.domain.AccountingEntry;
import org.springframework.stereotype.Service;
import com.fehasite.site.customization.domain.Customization;
import com.fehasite.site.customization.domain.CustomizationType;
import com.fehasite.site.order.domain.Order;
import com.fehasite.site.order.domain.OrderItem;
import com.fehasite.site.product.domain.Set;
import com.fehasite.site.product.domain.SetItem;

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



        // 1️⃣ Set’i al
        Set set = setRepository.findById(command.setId())
                .orElseThrow(() -> new IllegalArgumentException("Set not found"));

        // 2️⃣ Order oluştur
        Order order = new Order(command.finalPriceCents());

        // 3️⃣ Set snapshot → OrderItem
        for (SetItem setItem : set.getItems()) {
            OrderItem item = new OrderItem(
                    setItem.getProduct().getName(),
                    command.finalPriceCents(), // basit, sonra ayıracağız
                    setItem.getQuantity()
            );


            order.addItem(item);

        }

        // 4️⃣ Order save (ID oluşur)
        Order savedOrder = orderRepository.save(order);

        // 5️⃣ Customization oluştur
        Customization customization;

        if (command.customizationType() == CustomizationType.TEXT) {
            customization = Customization.textCustomization(
                    savedOrder.getId(),
                    command.productionMethod(),
                    command.textValue()
            );
        } else {
            customization = Customization.imageCustomization(
                    savedOrder.getId(),
                    command.productionMethod(),
                    command.imageKey()
            );

        }


        customizationRepository.save(customization);
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
