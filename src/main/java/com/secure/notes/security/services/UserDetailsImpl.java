package com.secure.notes.security.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private boolean is2faEnabled;

    public Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public UserDetailsImpl(Long id, String username, String email,
                           String password, boolean is2faEnabled,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is2faEnabled = is2faEnabled;
        this.authorities = authorities;
    }

    // Static method to build UserDetailsImpl from a custom User entity
    public static UserDetailsImpl build(com.secure.notes.models.User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
        return new UserDetailsImpl(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.isTwoFactorEnabled(),
                List.of(authority)
        );
    }

    // Implementing the UserDetails interface methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Return the actual authorities list
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}