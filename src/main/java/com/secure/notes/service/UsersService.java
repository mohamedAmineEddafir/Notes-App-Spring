package com.secure.notes.service;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.User;

import java.util.List;

public interface UsersService {
    void updateUserRole(Long user_id, String roleName);

    List<User> getUsers();

    UserDTO getUserById(Long user_id);
}
