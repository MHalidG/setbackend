package com.fehasite.site.accounting.api;

import com.fehasite.site.accounting.application.AccountingReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminAccountingController {

    private final AccountingReportService reportService;

    public AdminAccountingController(AccountingReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/revenue")
    public long monthlyRevenue(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return reportService.monthlyRevenue(year, month);
    }
}
