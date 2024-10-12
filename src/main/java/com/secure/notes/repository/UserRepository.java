package com.secure.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.secure.notes.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
