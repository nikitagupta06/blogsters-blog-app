package com.nikitagupta06.blogsters.controller;

import com.nikitagupta06.blogsters.model.User;
import com.nikitagupta06.blogsters.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        log.info("Creating user {}", user);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> allUsersList = userService.findAllUsers();
        if (allUsersList != null) {
            return ResponseEntity.ok().body(allUsersList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{user_id}")
    public Optional<User> findUserById(@PathVariable Long user_id) {
        return userService.findUserById(user_id);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        Optional<User> currentUser = userService.findUserById(userId);
        if (currentUser != null) {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
