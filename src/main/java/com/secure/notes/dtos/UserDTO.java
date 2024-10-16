package com.secure.notes.dtos;

import com.secure.notes.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private LocalDate credentialsExpiredDate;
    private LocalDate accountExpiredDate;
    private String twoFactorSecret;
    private boolean twoFactorEnabled;
    private String signUpMethod;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
