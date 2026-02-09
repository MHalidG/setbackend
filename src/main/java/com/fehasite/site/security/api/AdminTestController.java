package com.fehasite.site.security.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminTestController {

    @GetMapping("/api/admin/test")
    public String test() {
        return "ADMIN OK";
    }
}
