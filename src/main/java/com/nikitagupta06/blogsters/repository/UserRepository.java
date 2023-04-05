package com.nikitagupta06.blogsters.repository;

import com.nikitagupta06.blogsters.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
