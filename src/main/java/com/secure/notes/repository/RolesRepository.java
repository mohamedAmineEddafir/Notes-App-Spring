package com.secure.notes.repository;


import com.secure.notes.models.AppRoles;
import com.secure.notes.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleName(AppRoles roleName);
}
