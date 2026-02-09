package com.fehasite.site.infrastructure.persistence.accounting;

import com.fehasite.site.accounting.application.AccountingRepository;
import com.fehasite.site.accounting.domain.AccountingEntry;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public class AccountingRepositoryAdapter implements AccountingRepository {

    private final JpaAccountingEntryRepository jpaRepository;

    public AccountingRepositoryAdapter(JpaAccountingEntryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public AccountingEntry save(AccountingEntry entry) {
        return jpaRepository.save(entry);
    }

    @Override
    public long sumRevenueBetween(Instant start, Instant end) {
        return jpaRepository.sumRevenueBetween(start, end);
    }
}
