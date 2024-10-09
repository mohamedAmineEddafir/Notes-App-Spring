package com.secure.notes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@Data
@Entity
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long UserId;

    @NotBlank
    @Size(min = 5, max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(min = 5, max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 50)
    @JsonIgnore
    private String password;

    private boolean enabled;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;

    private LocalDate credentialsExpiredDate;
    private LocalDate accountExpiredDate;

    // on SignUp Method
    private String towFactorSecret;
    private boolean towFactorEnabled = true;
    private String signUpMethod; // to keep track how user signup (Google, GitHub, etc...)

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonBackReference
    @ToString.Exclude
    private Role role;
    public User() {}

}
