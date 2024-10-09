package com.secure.notes.repository;

import com.secure.notes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String userName);
}
