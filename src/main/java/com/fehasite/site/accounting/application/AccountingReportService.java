package com.fehasite.site.accounting.application;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
@Service
public class AccountingReportService {

    private final AccountingRepository repository;

    public AccountingReportService(AccountingRepository repository) {
        this.repository = repository;
    }

    public long monthlyRevenue(int year, int month) {

        Instant start = LocalDate.of(year, month, 1)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);

        Instant end = LocalDate.of(year, month, 1)
                .plusMonths(1)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);

        return repository.sumRevenueBetween(start, end);
    }
}
