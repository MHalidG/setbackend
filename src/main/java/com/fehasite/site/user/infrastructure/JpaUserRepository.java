package com.fehasite.site.user.infrastructure;

import com.fehasite.site.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface JpaUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
