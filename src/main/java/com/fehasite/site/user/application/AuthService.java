package com.fehasite.site.user.application;

import com.fehasite.site.security.JwtUtil;
import com.fehasite.site.security.SecurityRole;
import com.fehasite.site.user.domain.Role;
import com.fehasite.site.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String rawPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        SecurityRole securityRole =
                user.getRole() == Role.ADMIN
                        ? SecurityRole.ADMIN
                        : SecurityRole.USER;

        return jwtUtil.generateToken(user.getEmail(), securityRole);
    }
}
