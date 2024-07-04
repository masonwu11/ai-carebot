package com.aicarebot.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicarebot.api.daos.RegisterUserDto;
import com.aicarebot.api.daos.User;
import com.aicarebot.api.services.AuthenticationService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    logger.info("Signup request received with the following data: {}", registerUserDto.toString());
    boolean validationResult = authenticationService.validate(registerUserDto) == null ? false : true;
    if (validationResult) {
      logger.warn("User with email: {} already exists", registerUserDto.getEmail());
      return ResponseEntity.status(409).build();
    }
    User registeredUser = authenticationService.signup(registerUserDto);
    return ResponseEntity.ok(registeredUser);
  }

}
