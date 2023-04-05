package com.nikitagupta06.blogsters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "user_seq", allocationSize = 1, initialValue = 1000)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    @NotNull(message = "Username is required")
    @Size(max = 20, message = "Username can have maximum of 20 characters")
    @Column(nullable = false, unique = true)
    private String username;
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password should have minimum of 6 characters")
    private String password;
    @NotNull(message = "Email is required")
    @Size(max = 50, message = "Email can have maximum of 50 characters")
    @Email(message = "Invalid email address")
    private String email;
    @NotNull
    @Size(max = 20, message = "Name can be maximum of 20 characters")
    private String full_name;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
