package com.fehasite.site.accounting.application;

import com.fehasite.site.accounting.domain.AccountingEntry;

import java.time.Instant;

public interface AccountingRepository {
    AccountingEntry save(AccountingEntry entry);
    long sumRevenueBetween(Instant start, Instant end);


}
