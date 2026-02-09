package com.fehasite.site.security.api;

import com.fehasite.site.user.application.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(
                request.email(),
                request.password()
        );
        return new TokenResponse(token);
    }

    public record LoginRequest(String email, String password) {}
    public record TokenResponse(String token) {}
}
