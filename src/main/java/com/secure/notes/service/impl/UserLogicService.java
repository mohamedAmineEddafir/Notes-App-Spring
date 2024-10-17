package com.secure.notes.service.impl;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.AppRoles;
import com.secure.notes.models.Role;
import com.secure.notes.models.User;
import com.secure.notes.repository.RolesRepository;
import com.secure.notes.repository.UserRepository;
import com.secure.notes.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogicService implements UsersService {

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    @Autowired
    public UserLogicService(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void updateUserRole(Long user_id, String roleName) {
        User user = userRepository.findById(user_id).orElseThrow(()->
                new RuntimeException("User Not Found"));
        Role newRole = rolesRepository.findByRoleName(AppRoles.valueOf(roleName)).orElseThrow(
                ()->new RuntimeException("Role Not Found"+ roleName)
        );
        user.setRole(newRole);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(Long user_id) {
        return null;
    }
}
