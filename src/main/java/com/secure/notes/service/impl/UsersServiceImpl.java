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
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Role role = rolesRepository.findByRoleName(AppRoles.valueOf(roleName))
                    .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
            user.setRole(role);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getCredentialsExpiredDate(),
                user.getAccountExpiredDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
