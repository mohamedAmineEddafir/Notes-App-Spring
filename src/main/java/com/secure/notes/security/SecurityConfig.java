package com.secure.notes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import javax.sql.DataSource;

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

    // Method to add users after JdbcUserDetailsManager is initialized
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource);
        if (!userManager.userExists("user")){
            userManager.createUser(
                    User.withUsername("amine")
                    .password(passwordEncoder.encode("123"))
                    .authorities("ROLE_USER")
                    .disabled(true)
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
                    .disabled(true)
                    .build()
            );
        } else {
            throw new UsernameNotFoundException("Admin not found");
        }
        return userManager;
    }
}
