package com.aicarebot.api.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicarebot.api.daos.User;
import com.aicarebot.api.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("*****************************");
        logger.info("Received request to get all users");
        logger.info("*****************************");

        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.info("*****************************");
        logger.info("Received request to get user with ID: {}", id);
        logger.info("*****************************");

        Optional<User> optionUser = userService.getUserById(id);

        if (optionUser.isPresent()) {
            logger.info("User found: {}", optionUser.get());
            return ResponseEntity.ok(optionUser.get());
        } else {
            logger.warn("User with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        logger.info("*****************************");
        logger.info("Received request to get user with email: {}", email);
        logger.info("*****************************");

        Optional<User> optionUser = userService.getUserByEmail(email);

        if (optionUser.isPresent()) {
            logger.info("User found: {}", optionUser.get());
            return ResponseEntity.ok(optionUser.get());
        } else {
            logger.warn("User with email: {} not found", email);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("*****************************");
        logger.info("Received request to create user: {}", user);
        logger.info("*****************************");

        // check if email exists
        Optional<User> optionUser = userService.getUserByEmail(user.getEmail());
        if (optionUser.isPresent()) {
            logger.info("Updated user {} info", user.getId());
            User createdUser = userService.updateUser(optionUser.get().getId(), user).get();
            return ResponseEntity.ok(createdUser);
        }
        else {
            logger.info("User not found, creating new user");
            User createdUser = userService.addUser(user);
            return ResponseEntity.ok(createdUser);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        logger.info("*****************************");
        logger.info("Received request to update user with ID: {}", id);
        logger.info("*****************************");

        Optional<User> user = userService.updateUser(id, updatedUser);
        return user.map(u -> {
            logger.info("User updated: {}", u);
            return ResponseEntity.ok(u);
        }).orElseGet(() -> {
            logger.warn("User with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        });
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("*****************************");
        logger.info("Received request to delete user with ID: {}", id);
        logger.info("*****************************");
        
        userService.deleteUser(id);
        logger.info("User with ID: {} deleted", id);
        return ResponseEntity.ok().build();
    
    }
    
}
