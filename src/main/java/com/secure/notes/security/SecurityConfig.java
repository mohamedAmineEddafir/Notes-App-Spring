package com.secure.notes.security;

import com.secure.notes.models.AppRoles;
import com.secure.notes.models.Role;
import com.secure.notes.models.User;
import com.secure.notes.repository.RolesRepository;
import com.secure.notes.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/public/**").permitAll()
                                .anyRequest().authenticated()
                );
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public CommandLineRunner initData(RolesRepository rolesRepository, UserRepository userRepository) {
        return args -> {
            Role userRole = rolesRepository.findByRoleName(AppRoles.ROLE_USER)
                    .orElseGet(() -> rolesRepository.save(new Role(AppRoles.ROLE_USER)));

            Role adminRole = rolesRepository.findByRoleName(AppRoles.ROLE_ADMIN)
                    .orElseGet(() -> rolesRepository.save(new Role(AppRoles.ROLE_ADMIN)));

            if (!userRepository.existsByUsername("user1")) {
                User user1 = new User("user1", "user1@gmail.com", "{noop}12345678");
                user1.setAccountNonLocked(true);
                user1.setAccountNonExpired(true);
                user1.setCredentialsNonExpired(true);
                user1.setEnabled(true);
                user1.setCredentialsExpiredDate(LocalDate.now().plusYears(1));
                user1.setAccountExpiredDate(LocalDate.now().plusYears(1));
                user1.setTwoFactorEnabled(false);
                user1.setSignUpMethod("email");
                user1.setRole(userRole);
                userRepository.save(user1);
            }

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User("admin", "admin@gmail.com", "{noop}12345678");
                admin.setAccountNonLocked(true);
                admin.setAccountNonExpired(true);
                admin.setCredentialsNonExpired(true);
                admin.setEnabled(true);
                admin.setCredentialsExpiredDate(LocalDate.now().plusYears(1));
                admin.setAccountExpiredDate(LocalDate.now().plusYears(1));
                admin.setTwoFactorEnabled(false);
                admin.setSignUpMethod("email");
                admin.setRole(adminRole);
                userRepository.save(admin);
            }
        };
    }
}
