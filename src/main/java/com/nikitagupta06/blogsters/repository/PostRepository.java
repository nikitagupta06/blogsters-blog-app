package com.nikitagupta06.blogsters.repository;

import com.nikitagupta06.blogsters.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
