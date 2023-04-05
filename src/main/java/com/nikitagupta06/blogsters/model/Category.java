package com.nikitagupta06.blogsters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    private List<Post> posts;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }
}
