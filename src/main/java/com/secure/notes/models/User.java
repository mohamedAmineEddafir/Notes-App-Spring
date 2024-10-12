package com.secure.notes.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;
import java.time.LocalDate;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
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
    public Long userId;

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

    /*
        * towFactorSecret * stores the secret key for 2FA,
        while * towFactorEnabled * is a boolean indicating if 2FA is enabled.
        * signUpMethod * tracks how a user signed up (Google, GitHub, etc.),
        which is useful for handling different authentication flows.
    */

    private String twoFactorSecret;
    private boolean twoFactorEnabled = true;
    private String signUpMethod;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonBackReference
    @ToString.Exclude
    private Role role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // this timestamp is regenerated every time in instance is updated in the database
    private LocalDateTime updatedAt;

    public User(){}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled && accountNonExpired ==
                user.accountNonExpired && credentialsNonExpired ==
                user.credentialsNonExpired && accountNonLocked ==
                user.accountNonLocked && twoFactorEnabled ==
                user.twoFactorEnabled && Objects.equals(userId, user.userId)
                && Objects.equals(username, user.username) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && Objects.equals(credentialsExpiredDate, user.credentialsExpiredDate)
                && Objects.equals(accountExpiredDate, user.accountExpiredDate) && Objects.equals(twoFactorSecret, user.twoFactorSecret)
                && Objects.equals(signUpMethod, user.signUpMethod) && Objects.equals(role, user.role) && Objects.equals(createdAt, user.createdAt)
                && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, credentialsExpiredDate, accountExpiredDate,
                twoFactorSecret, twoFactorEnabled, signUpMethod, role, createdAt, updatedAt);
    }
}
