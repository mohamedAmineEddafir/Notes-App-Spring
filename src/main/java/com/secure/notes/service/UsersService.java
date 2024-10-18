package com.secure.notes.service;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.User;

import java.util.List;

public interface UsersService {
    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);
}
