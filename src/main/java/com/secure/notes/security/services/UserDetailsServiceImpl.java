package com.secure.notes.security.services;

import org.springframework.stereotype.Service;
import com.secure.notes.repository.UserRepository;
import com.secure.notes.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Injecting the UserRepository to access user data
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method is called by Spring Security to load user by their username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user in the database by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Build and return a UserDetailsImpl object using the found user
        return UserDetailsImpl.build(user);
    }
}
