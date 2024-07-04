package com.aicarebot.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aicarebot.api.daos.RegisterUserDto;
import com.aicarebot.api.daos.User;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setName(input.getName());
        return userService.addUser(user);
    }

    public User validate(RegisterUserDto input) {
        return userService.getUserByEmail(input.getEmail()).orElse(null);
    }
}
