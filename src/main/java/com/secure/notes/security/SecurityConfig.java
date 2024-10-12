package com.secure.notes.security;

import com.secure.notes.models.AppRoles;
import com.secure.notes.models.Role;
import com.secure.notes.models.User;
import com.secure.notes.repository.RolesRepository;
import com.secure.notes.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import javax.sql.DataSource;

import java.time.LocalDate;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        //http.formLogin(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(withDefaults());
        return http.build();
    }
/*    @Bean
    public CommandLineRunner initData(RolesRepository rolesRepository, UserRepository userRepository) {
        return args -> {
            // Create roles if they don't exist
            Role userRole = rolesRepository.findByRoleName(AppRoles.ROLE_USER)
                    .orElseGet(() -> rolesRepository.save(new Role(AppRoles.ROLE_USER)));

            Role adminRole = rolesRepository.findByRoleName(AppRoles.ROLE_ADMIN)
                    .orElseGet(() -> rolesRepository.save(new Role(AppRoles.ROLE_ADMIN)));

            // Create user1 if not exists
            if (!userRepository.existsByUsername("user1")) {
                User user1 = new User("user1", "user1@example.com", "{noop}password1");
                user1.setAccountNonLocked(false);
                user1.setAccountNonExpired(true);
                user1.setCredentialsNonExpired(true);
                user1.setEnabled(true);
                user1.setCredentialsExpiredDate(LocalDate.now().plusYears(1));
                user1.setAccountExpiredDate(LocalDate.now().plusYears(1));
                user1.setTwoFactorEnabled(false);
                user1.setSignUpMethod("email");
                user1.setRole(userRole);  // Assign the role
                userRepository.save(user1);
            }

            // Create admin if not exists
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User("admin", "admin@example.com", "{noop}adminPass");
                admin.setAccountNonLocked(true);
                admin.setAccountNonExpired(true);
                admin.setCredentialsNonExpired(true);
                admin.setEnabled(true);
                admin.setCredentialsExpiredDate(LocalDate.now().plusYears(1));
                admin.setAccountExpiredDate(LocalDate.now().plusYears(1));
                admin.setTwoFactorEnabled(false);
                admin.setSignUpMethod("email");
                admin.setRole(adminRole);  // Assign the admin role
                userRepository.save(admin);
            }
        };
    }*/

    // Method to add users after JdbcUserDetailsManager is initialized
/*    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource);
        if (!userManager.userExists("user")){
            userManager.createUser(
                    User.withUsername("amine")
                    .password(passwordEncoder.encode("123"))
                    .authorities("ROLE_USER")
                    .build()
            );
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        if (!userManager.userExists("admin")){
            userManager.createUser(
                    User.withUsername("admin")
                    .password(passwordEncoder.encode("@dmin"))
                    .authorities("ROLE_ADMIN")
                    .build()
            );
        } else {
            throw new UsernameNotFoundException("Admin not found");
        }

        return userManager;
    }*/
}
