package com.nikitagupta06.blogsters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Tag name can not be empty")
    private String name;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

//    @ManyToMany(mappedBy = "tags")
//    private Set<Post> posts = new HashSet<>();

}
