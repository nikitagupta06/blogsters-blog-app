package com.nikitagupta06.blogsters.repository;

import com.nikitagupta06.blogsters.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
