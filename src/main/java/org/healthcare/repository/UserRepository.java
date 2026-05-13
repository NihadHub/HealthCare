package org.healthcare.repository;

import jakarta.validation.constraints.NotBlank;
import org.healthcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String name);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
