package com.fehasite.site.accounting.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "accounting_entries")
public class AccountingEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private int amountCents;

    @Column(nullable = false)
    private Instant createdAt;

    protected AccountingEntry() {
        // JPA
    }

    private AccountingEntry(Long orderId, int amountCents) {
        this.orderId = orderId;
        this.amountCents = amountCents;
        this.createdAt = Instant.now();
    }

    public static AccountingEntry sale(Long orderId, int amountCents) {
        if (amountCents <= 0) {
            throw new IllegalArgumentException("Accounting amount must be positive");
        }
        return new AccountingEntry(orderId, amountCents);
    }

    public Long getOrderId() {
        return orderId;
    }

    public int getAmountCents() {
        return amountCents;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
