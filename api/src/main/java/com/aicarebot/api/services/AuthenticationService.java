package com.aicarebot.api.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aicarebot.api.daos.LoginUserDao;
import com.aicarebot.api.daos.RegisterUserDto;
import com.aicarebot.api.daos.User;

@Service
public class AuthenticationService {
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

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

    public User authenticate(LoginUserDao input) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        
        return userService.getUserByEmail(input.getEmail()).orElseThrow();
    }
}
