package com.fehasite.site.infrastructure.persistence.accounting;

import com.fehasite.site.accounting.domain.AccountingEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface JpaAccountingEntryRepository
        extends JpaRepository<AccountingEntry, Long> {

    @Query("""
        SELECT COALESCE(SUM(a.amountCents), 0)
        FROM AccountingEntry a
        WHERE a.createdAt BETWEEN :start AND :end
    """)
    long sumRevenueBetween(Instant start, Instant end);
}
